package com.xuyao.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnno {
    public enum Priority {L, M, H}
    public enum Status {S, E}
    String author() default "";
    Priority priority() default Priority.L;
    Status status() default  Status.S;

}
