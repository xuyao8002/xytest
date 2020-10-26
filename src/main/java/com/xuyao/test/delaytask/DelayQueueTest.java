package com.xuyao.test.delaytask;


import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayObject> delayQueue = new DelayQueue<>();
        delayQueue.add(new DelayObject("a", 1000));
        delayQueue.add(new DelayObject("b", 4000));
        delayQueue.add(new DelayObject("c", 3000));
        delayQueue.add(new DelayObject("d", 2000));
        int size = delayQueue.size();
        for (int i = 0; i < size; i++) {
            System.out.println(delayQueue.take());
        }
    }

}


class DelayObject implements Delayed {
    private String data;
    private long startTime;

    public DelayObject(String data, long startTime) {
        this.data = data;
        this.startTime = System.currentTimeMillis() + startTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(startTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed obj) {
        return Long.compare(this.startTime, ((DelayObject) obj).startTime);
    }

    @Override
    public String toString() {
        return "DelayObject{" +
                "data='" + data + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}