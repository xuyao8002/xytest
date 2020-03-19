package com.xuyao.test.bytecode;

import java.util.HashMap;
import java.util.Map;

public class TimeCache {
    public static Map<String, Long> methodStartTime = new HashMap<>();
    public static Map<String, Long> methodEndTime = new HashMap<>();
    public static void setStartTime(String methodName, long time) {
        methodStartTime.put(methodName, time);
    }
    public static void setEndTime(String methodName, long time) {
        methodEndTime.put(methodName, time);
    }
    public static String getCostTime(String methodName) {
        long start = methodStartTime.get(methodName);
        long end = methodEndTime.get(methodName);
        return "method: " + methodName + " " +  Long.valueOf(end - start) + " ms";
    }


    public static void main(String[] args) {
        System.out.println("========start=========");
        TimeCache.setStartTime("methodName", System.currentTimeMillis());

        TimeCache.setEndTime("methodName", System.currentTimeMillis());
        System.out.println(TimeCache.getCostTime("methodName"));
        System.out.println("========end=========");
    }
}