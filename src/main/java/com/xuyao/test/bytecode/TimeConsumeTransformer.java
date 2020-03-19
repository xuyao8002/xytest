package com.xuyao.test.bytecode;

import com.xuyao.test.bytecode.asm.TimeConsumeVisitor;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class TimeConsumeTransformer implements ClassFileTransformer {
    public static void premain(String args, Instrumentation inst) {
        inst.addTransformer(new TimeConsumeTransformer());
    }
    @Override
    public byte[] transform(ClassLoader loader, String className, Class classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        ClassReader reader = new ClassReader(classfileBuffer);
        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS);
        reader.accept(new TimeConsumeVisitor(writer), 8);
        return writer.toByteArray();
    }
}