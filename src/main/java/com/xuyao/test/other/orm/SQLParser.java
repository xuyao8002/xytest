package com.xuyao.test.other.orm;

import ognl.OgnlException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.tree.DefaultElement;
import org.dom4j.tree.DefaultText;

import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLParser {

    /**
     * 零宽断言，匹配赋值表达式中的字段名称
     */
    private static final Pattern FIELD_PATTERN = Pattern.compile("(?<=#\\{)\\w+(?=\\})");

    private static final String PLACEHOLDER_REGEX = "#\\{\\w+\\}";

    /**
     * 解析sql语句
     * @param nodes sql信息集合
     * @param object 参数对象
     * @param paramValues 占位符对应的字段值
     * @return
     * @throws OgnlException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static String parse(List<Node> nodes, Object object, List paramValues) throws OgnlException, NoSuchFieldException, IllegalAccessException {
        StringBuilder sql = new StringBuilder();
        for (Node node : nodes) {
            //普通节点，直接返回文本值
            if(node.getClass() == DefaultText.class){
                String text = node.getText();
                sql.append(replacePlaceholder(text));
                getParamValues(object, paramValues, text);
            }else if(node.getClass() == DefaultElement.class){
                Element element = (Element) node;
                //目前只处理if标签
                boolean anIf = element.getQName().getName().equals("if");
                if(anIf && object != null){
                    //获取boolean表达式
                    String test = element.attributeValue("test");
                    Boolean result = OgnlUtils.getOgnlValue(object, test);
                    if(result){
                        //替换赋值表达式为“?”
                        String text = element.getText();
                        sql.append(replacePlaceholder(text));
                        getParamValues(object, paramValues, text);
                    }
                }

            }
        }
        return sql.toString();
    }

    /**
     * 获取赋值表达式中的字段名称
     */
    private static void getParamValues(Object object, List params, String text) throws NoSuchFieldException, IllegalAccessException {
        Matcher matcher = FIELD_PATTERN.matcher(text);
        while (matcher.find()) {
            String fieldName = matcher.group();
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            //从参数中获取字段值
            params.add(field.get(object));
        }
    }

    private static String replacePlaceholder(String sql){
        return sql.replaceAll(PLACEHOLDER_REGEX, "?");
    }


}
