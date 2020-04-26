package com.xuyao.test.other.format;

import java.text.MessageFormat;

public class MessageFormatDemo {

    public static void main(String[] args) {
        String str = MessageFormat.format("hello {0}, my name is {1}", "world", "java");
        System.out.println(str);

        //json字符串格式化时首尾括号要用'号包围
        String json = MessageFormat.format("'{'\"name\":\"{0}\"'}'", "honey");
        System.out.println(json);
    }

}
