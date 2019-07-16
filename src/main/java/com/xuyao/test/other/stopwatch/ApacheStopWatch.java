package com.xuyao.test.other.stopwatch;

import org.apache.commons.lang3.time.StopWatch;

public class ApacheStopWatch {

    /**
     * reset(); //重置计时
     * suspend(); //暂停计时
     * resume(); //恢复计时
     * stop(); //停止计时
     */

    public static void main(String[] args) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        Thread.sleep(10L);
        System.out.println(watch.getTime()); //start到当前时间
        Thread.sleep(15L);
        watch.split(); //设置split时间点
        System.out.println(watch.getSplitTime()); //start到当前split的时间
        Thread.sleep(20L);
        watch.split();
        System.out.println(watch.getSplitTime());
        watch.stop();
        System.out.println(watch.getTime());
    }

}
