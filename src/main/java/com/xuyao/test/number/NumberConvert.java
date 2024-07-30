package com.xuyao.test.number;

public class NumberConvert {

    public static int toDecimal(String binary, int radix) {
        return Integer.parseInt(binary, radix);
    }

    public static String toBinary(int decimal) {
        return Integer.toBinaryString(decimal);
    }

    public static String toOctal(int decimal) {
        return Integer.toOctalString(decimal);
    }

    public static String toHex(int decimal) {
        return Integer.toHexString(decimal);
    }
}
