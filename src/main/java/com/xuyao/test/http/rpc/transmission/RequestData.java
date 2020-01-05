package com.xuyao.test.http.rpc.transmission;


import java.io.Serializable;
import java.util.Arrays;

public class RequestData implements Serializable {

    private String methodName;

    private Class[] argsTypes;

    private Object[] args;

    private String serviceName;

    private Class<?> returnType;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getArgsTypes() {
        return argsTypes;
    }

    public void setArgsTypes(Class[] argsTypes) {
        this.argsTypes = argsTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "methodName='" + methodName + '\'' +
                ", argsTypes=" + Arrays.toString(argsTypes) +
                ", args=" + Arrays.toString(args) +
                ", serviceName='" + serviceName + '\'' +
                ", returnType=" + returnType +
                '}';
    }
}
