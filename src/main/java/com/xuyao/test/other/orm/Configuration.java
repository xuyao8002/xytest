package com.xuyao.test.other.orm;

import ognl.OgnlException;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.sql.*;
import java.util.*;

/**
 * 主配置类
 */
public class Configuration {

    /**
     * 类加载器
     */
    private ClassLoader loader;

    private Connection connection;

    private Map<Class<?>, MapperInfo> mapperInfoMap = new HashMap<>();

    private Map<String, Class<?>> loadedClassMap = new HashMap<>();

    public Configuration() {
        this.loader = ClassLoader.getSystemClassLoader();
    }

    public void build(String resourceName) throws DocumentException, SQLException, ClassNotFoundException {
        DataSource dataSource = initDataSource(resourceName);
        Class.forName(dataSource.getDriverClassName());
        connection = DriverManager.getConnection(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
    }

    /**
     * 从resources下指定子目录加载mapper文件信息
     * @param resourcePath
     * @param resourceSuffix
     * @return
     * @throws FileNotFoundException
     * @throws DocumentException
     * @throws ClassNotFoundException
     */
    public void loadMapperInfos(String resourcePath, String resourceSuffix) throws FileNotFoundException, DocumentException, ClassNotFoundException {
        if(mapperInfoMap.isEmpty()){
            URL resource = loader.getResource(resourcePath);
            File resourceDir = new File(resource.getFile());
            File[] files = resourceDir.listFiles((dir, name) -> name.endsWith(resourceSuffix));
            for (File file : files) {
                initMapperInfo(file);
            }
        }
    }

    private void initMapperInfo(File file) throws DocumentException, FileNotFoundException, ClassNotFoundException {
        MapperInfo mapperInfo = new MapperInfo();
        Map<String, StatementInfo> statementInfoMap = new HashMap<>();
        mapperInfo.setStatementInfoMap(statementInfoMap);

        SAXReader reader = new SAXReader();
        Document document = reader.read(new FileInputStream(file));

        //根元素解析
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        if (StringUtils.isBlank(namespace)) {
            throw new NullPointerException("namespace can't be null");
        }
        Class<?> daoClass = getLoadedClass(namespace);
        mapperInfo.setMapperProxy(createMapperProxy(daoClass));

        mapperInfoMap.put(daoClass, mapperInfo);

        Iterator<Element> elementIterator = rootElement.elementIterator();
        while (elementIterator.hasNext()) {
            //子元素解析
            Element next = elementIterator.next();
            StatementInfo statementInfo = new StatementInfo();
            statementInfo.setType(next.getQName().getName());
            String id = next.attributeValue("id");
            statementInfo.setId(id);
            statementInfo.setParamType(next.attributeValue("paramType"));
            statementInfo.setResultType(getLoadedClass(next.attributeValue("resultType")));
            statementInfo.setSqlNodes(next.content());
            statementInfoMap.put(id, statementInfo);
        }
    }

    private Class<?> getLoadedClass(String className) throws ClassNotFoundException {
        Class<?> loadedClass = loadedClassMap.get(className);
        if(loadedClass == null){
            loadedClass = loader.loadClass(className);
            loadedClassMap.put(className, loadedClass);
        }
        return loadedClass;
    }

    private <T> T createMapperProxy(Class<T> clazz){
        return (T) Proxy.newProxyInstance(this.loader, new Class[]{clazz}, (proxy, method, args) -> {
            Class<?> returnType = method.getReturnType();
            MapperInfo mapperInfo = mapperInfoMap.get(clazz);
            Map<String, StatementInfo> statementInfoMap = mapperInfo.getStatementInfoMap();
            String name = method.getName();
            StatementInfo statementInfo = statementInfoMap.get(name);
            if (Objects.equals("select", statementInfo.getType())) {
                return handleSelect(statementInfo, returnType, args);
            }else{
                PreparedStatement preparedStatement = getPreparedStatement(statementInfo, args[0]);
                return preparedStatement.executeUpdate();
            }
        });
    }

    public <T> T getMapperProxy(Class<T> clazz){
        return (T) mapperInfoMap.get(clazz).getMapperProxy();
    }

    private PreparedStatement getPreparedStatement(StatementInfo statementInfo, Object arg) throws OgnlException, NoSuchFieldException, IllegalAccessException, SQLException {
        List<Object> params = new ArrayList<>();
        String sql = SQLParser.parse(statementInfo.getSqlNodes(), arg, params);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.size(); i++) {
            preparedStatement.setObject(i + 1, params.get(i));
        }
        return preparedStatement;
    }

    /**
     * 处理查询
     * @param statementInfo
     * @param returnType
     * @param args
     * @return
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws OgnlException
     * @throws InstantiationException
     */
    private Object handleSelect(StatementInfo statementInfo, Class<?> returnType, Object[] args) throws SQLException, IllegalAccessException, NoSuchFieldException, OgnlException, InstantiationException {
        PreparedStatement preparedStatement = getPreparedStatement(statementInfo, args[0]);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet != null){
            //是否查询多条记录
            boolean selectMany = Collection.class.isAssignableFrom(returnType);
            if (!selectMany && resultSet.last()) {
                if(resultSet.getRow() > 1){
                    throw new RuntimeException("should return one but return many");
                }
                resultSet.beforeFirst();
            }
            //获取查询返回字段名称
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] columnNames = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columnNames[i] = metaData.getColumnName(i+1);
            }
            Class resultType = statementInfo.getResultType();
            if(selectMany){
                List<Object> list = new ArrayList<>();
                while (resultSet.next()) {
                    Object instance = resultType.newInstance();
                    list.add(instance);
                    setValueToInstance(resultSet, columnNames, resultType, instance);
                }
                return list;
            }else{
                Object instance = resultType.newInstance();
                while (resultSet.next()) {
                    setValueToInstance(resultSet, columnNames, resultType, instance);
                    break;
                }
                return instance;
            }
        }
        return null;
    }

    private void setValueToInstance(ResultSet resultSet, String[] columnNames, Class resultType, Object instance) throws IllegalAccessException, SQLException {
        for (String columnName : columnNames) {
            Field field;
            try {
                field = resultType.getDeclaredField(columnName);
            } catch (NoSuchFieldException e) {
                continue;
            }
            field.setAccessible(true);
            field.set(instance, resultSet.getObject(columnName));
        }
    }

    /**
     * 初始数据源信息
     * @param resourceName
     * @return
     * @throws DocumentException
     */
    private DataSource initDataSource(String resourceName) throws DocumentException {
        InputStream resourceAsStream = loader.getResourceAsStream(resourceName);
        SAXReader reader = new SAXReader();
        Document document = reader.read(resourceAsStream);
        Element rootElement = document.getRootElement();
        Iterator<Element> elementIterator = rootElement.elementIterator();
        DataSource dataSource = new DataSource();
        while (elementIterator.hasNext()) {
            Element next = elementIterator.next();
            String name = next.attributeValue("name");
            String value = next.getText();
            if ("driverClassName".equals(name)) {
                dataSource.setDriverClassName(value);
            }else if("url".equals(name)){
                dataSource.setUrl(value);
            }else if("username".equals(name)){
                dataSource.setUsername(value);
            }else if("password".equals(name)){
                dataSource.setPassword(value);
            }
        }
        return dataSource;
    }

}
