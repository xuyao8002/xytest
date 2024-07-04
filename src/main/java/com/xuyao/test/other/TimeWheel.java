package com.xuyao.test.other;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class TimeWheel {
    private int tickDuration; // 每个时间槽的时间间隔，单位毫秒
    private int wheelSize; // 时间轮的大小
    private List<BlockingQueue<Runnable>> timeSlots; // 时间轮的时间槽队列
    private ScheduledExecutorService scheduledExecutor; // 执行定时任务的线程池
    private int currentIndex; // 当前时间槽的索引
    private DelayQueue<DelayedTask> delayedTasks; // 延迟时间超过一个轮次的延迟队列
    private ExecutorService executor; //执行任务线程池


    public TimeWheel(int tickDuration, int wheelSize) {
        this.tickDuration = tickDuration;
        this.wheelSize = wheelSize;
        this.timeSlots = new ArrayList<>(wheelSize);
        for (int i = 0; i < wheelSize; i++) {
            timeSlots.add(new LinkedBlockingQueue<>());
        }
        this.scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        this.currentIndex = 0;
        this.delayedTasks = new DelayQueue<>();
        executor = Executors.newFixedThreadPool(wheelSize);
    }


    public void addTask(Runnable task, int delay) {
        int interval = tickDuration * wheelSize;
        if (delay <= 0) {
            // 如果延迟时间小于等于0，直接执行任务
            executeTask(task);
        } else if (delay <= interval) {
            // 如果延迟时间在一个轮次范围内，放入对应的时间槽中
            int ticks = delay / tickDuration;
            int slot = (currentIndex + ticks) % wheelSize;
            timeSlots.get(slot).offer(task);
        } else {
            // 如果延迟时间超过一个轮次，放入延迟队列中延迟N轮，并计算延迟队列到期后剩余delay
            int targetTime = delay / interval * interval;
            delayedTasks.offer(new DelayedTask(task, targetTime, delay % targetTime));
        }
    }

    public void start() {
        scheduledExecutor.scheduleAtFixedRate(this::advance, tickDuration, tickDuration, TimeUnit.MILLISECONDS);
    }

    private void advance() {
        currentIndex = (currentIndex + 1) % wheelSize; // 更新当前时间槽的索引
        BlockingQueue<Runnable> currentSlot = timeSlots.get(currentIndex);

        // 执行当前时间槽中的所有任务
        Runnable task;
        while ((task = currentSlot.poll()) != null) {
            executeTask(task);
        }

        // 处理延迟时间超过一个轮次的任务
        DelayedTask delayedTask;
        while ((delayedTask = delayedTasks.poll()) != null) {
            addTask(delayedTask.getTask(), delayedTask.getTimeWheelDelay());
        }
    }

    private void executeTask(Runnable command) {
        executor.execute(command);
    }

    public void stop() {
        scheduledExecutor.shutdown();
        executor.shutdown();
    }

    public static void main(String[] args) {
        TimeWheel timeWheel = new TimeWheel(1000, 10); // 时间槽间隔为1秒，时间轮大小为10
        timeWheel.start();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        timeWheel.addTask(() -> System.out.println("task 1 " + format.format(new Date())), 2000);
        timeWheel.addTask(() -> System.out.println("task 2 " + format.format(new Date())), 4000);
        timeWheel.addTask(() -> System.out.println("task 3 " + format.format(new Date())), 15000);
        timeWheel.addTask(() -> System.out.println("task 4 " + format.format(new Date())), 16000);
        timeWheel.addTask(() -> System.out.println("task 5 " + format.format(new Date())), 21000);


        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timeWheel.stop();
    }
}

class DelayedTask implements Delayed {
    private Runnable task;
    private long targetTime;
    private int timeWheelDelay;

    public DelayedTask(Runnable task, long targetTime, int timeWheelDelay) {
        this.task = task;
        this.targetTime = targetTime + System.currentTimeMillis();
        this.timeWheelDelay = timeWheelDelay;
    }

    public Runnable getTask() {
        return task;
    }

    public int getTimeWheelDelay() {
        return timeWheelDelay;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.targetTime - System.currentTimeMillis(), unit);

    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS));
    }

}