package com.xuyao.test;

import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;
import com.xuyao.test.encrypt.base64.Base64Util;
import com.xuyao.test.other.ConsistentHash.ConsistentHash;
import jdk.nashorn.internal.runtime.options.Option;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTBarGroupingImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Test {

    private static boolean flag;

    public static void main(String[] args) throws Exception {
//        String[] ips = {"192.168.0.1", "192.168.0.2", "192.168.0.3", "192
//
// .168.0.4"};
//        ConsistentHash instance = ConsistentHash.getInstance(ips, 200);
//        String[] keys = {"a1", "b2", "c3", "d4", "e5", "f6", "g7", "h8", "i9", "j10","hello", "hehe"};
//        for (String key : keys) {
//            System.out.println(key + " -> " + instance.getNode(key));
//        }
//        String collect = Stream.of(new String[]{"hi",null,"nihao"}).filter(ss -> ss != null).collect(Collectors.joining("/"));
//        System.out.println(collect);

        String message = "hello,{0},{1}";
        System.out.println(MessageFormat.format(message, "你好", "world"));


    }



}
