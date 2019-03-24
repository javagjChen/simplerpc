package com.cgj.rpc;

import java.io.Serializable;

/**
 * @Classname RpcRequest
 * @Description TODO
 * @Date 2019/3/22 17:52
 * @Created by cgj
 */
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = -9100893052391757993L;
    private String className;
    private String methodName;
    private Object[] parameters;
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
