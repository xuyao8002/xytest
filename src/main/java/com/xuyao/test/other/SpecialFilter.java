package com.xuyao.test.other;

import org.apache.commons.lang3.StringUtils;

public class SpecialFilter {

    /**
     * 过滤emoji表情
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if (StringUtils.isNotBlank(source)) {
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "");
        } else {
            return source;
        }
    }

    /**
     * 过滤四字节字符
     * @param content
     * @return
     */
    public static String filterFourChar(String content) {
        byte[] conbyte = content.getBytes();
        for (int i = 0; i < conbyte.length; i++) {
            if ((conbyte[i] & 0xF8) == 0xF0) {
                for (int j = 0; j < 4; j++) {
                    conbyte[i+j]=0x30;
                }
                i += 3;
            }
        }
        return new String(conbyte).replaceAll("0000", "");
    }

}
