package com.xuyao.test.other.format;

import java.text.MessageFormat;

public class MessageFormatDemo {

    public static void main(String[] args) {
        String format = MessageFormat.format("hello {0}, my name is {1}", "world", "java");
        System.out.println(format);
    }

}
