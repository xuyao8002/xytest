package com.xuyao.test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    //数字
    String REGEX_DIGITAL = "^\\d+(.\\d+)?$";

    //9位整数，最多4位小数
    String pattern = "^\\d{1,9}(\\.\\d{1,4})?$";

    //键盘上特殊符号
    public static String symbol = "[`~!@#$%^&*()_\\\\\\-+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

    public static String getChinese(String cellValue){
        String regex = "[\\u4e00-\\u9fa5]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellValue);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return cellValue;
    }

    /**
     * 获取文本内中文、字母、数字
     * @param cellValue
     * @return
     */
    public static String getContentWithoutSymbol(String cellValue){
        String regex = "[\\u4e00-\\u9fa50-9A-Za-z]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellValue);
        String result = "";
        while (matcher.find()) {
            result += matcher.group(0);
        }
        return result;
    }

}
