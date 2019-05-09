package com.xuyao.test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    //数字
    String REGEX_DIGITAL = "^\\d+(.\\d+)?$";

    //9位整数，最多4位小数
    String pattern = "^\\d{1,9}(\\.\\d{1,4})?$";

    public static String getChinese(String cellValue){
        String regex = "[\\u4e00-\\u9fa5]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellValue);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return cellValue;
    }
}
