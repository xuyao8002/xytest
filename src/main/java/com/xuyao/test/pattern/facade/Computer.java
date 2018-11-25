package com.xuyao.test.pattern.facade;

/**
 * Created by xuyao on 2018/10/6.
 */
public class Computer {
    private CPU cpu;

    private Disk disk;

    private Memory memory;

    public Computer() {
        this.cpu = new CPU();
        this.disk = new Disk();
        this.memory = new Memory();
    }

    public void start(){
        cpu.start();
        disk.start();
        memory.start();
    }

    public void shutdown(){
        cpu.shutdown();
        disk.shutdown();
        memory.shutdown();
    }
}
