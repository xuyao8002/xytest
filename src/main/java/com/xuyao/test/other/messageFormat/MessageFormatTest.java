package com.xuyao.test.other.messageFormat;

import java.text.MessageFormat;

public class MessageFormatTest {

    public static void main(String[] args) {
        String pattern = "this is {0}, say {1}";
        String message = MessageFormat.format(pattern, "world", "hello");
        System.out.println(message);
    }

}
