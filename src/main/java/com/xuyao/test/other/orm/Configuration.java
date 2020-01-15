package com.xuyao.test.other.orm;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;

public class Configuration {

    private ClassLoader loader;

    private Connection connection;

    public Configuration() {
        this.loader = ClassLoader.getSystemClassLoader();
    }

    public Configuration build(String resourceName) throws DocumentException, SQLException, ClassNotFoundException {
        DataSource dataSource = initDataSource(resourceName);
        Class.forName(dataSource.getDriverClassName());
        connection = DriverManager.getConnection(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
        return this;
    }

    public Connection getConnection(){
        return connection;
    }

    public static void main(String[] args) throws DocumentException, SQLException, ClassNotFoundException {
        Configuration configuration = new Configuration();
        configuration.build("orm/orm.xml");
        System.out.println(configuration.getConnection());

    }

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

    private class DataSource{
        private String driverClassName;
        private String url;
        private String username;
        private String password;

        public String getDriverClassName() {
            return driverClassName;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "DataSource{" +
                    "driverClassName='" + driverClassName + '\'' +
                    ", url='" + url + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

}
