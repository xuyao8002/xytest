package com.xuyao.test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

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
