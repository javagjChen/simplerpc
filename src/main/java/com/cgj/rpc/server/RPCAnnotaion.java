package com.cgj.rpc.server;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname RPCAnnotaion
 * @Description TODO
 * @Date 2019/3/23 17:34
 * @Created by cgj
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RPCAnnotaion {

    Class<?> value();

    String version() default "";
}
