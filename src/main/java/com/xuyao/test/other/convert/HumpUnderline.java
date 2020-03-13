package com.xuyao.test.other.convert;


public class HumpUnderline {

    /**
     * 驼峰转下划线
     * @param str
     * @return
     */
    public static String toUnderline(String str){
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                if(i > 0 && Character.isUpperCase(str.charAt(i-1))){
                    sb.append(c);
                }else{
                    sb.append('_').append(Character.toLowerCase(c));
                }
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     * @param str
     * @return
     */
    public static String toHump(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if(c == '_'){
                continue;
            }
            if(i > 0 && str.charAt(i-1) == '_' && (i-1) > 0){
                sb.append(Character.toUpperCase(c));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
