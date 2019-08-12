package com.xuyao.test.other.benchmark;

import com.xuyao.test.Test1;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JMH {
    @Benchmark
    public void wellHelloThere() {
        // this method was intentionally left blank.
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                // 指明本次要跑的类
                .include(Test1.class.getSimpleName())
                // fork JVM的数量
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
