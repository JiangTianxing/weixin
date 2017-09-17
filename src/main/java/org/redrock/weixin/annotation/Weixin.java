package org.redrock.weixin.annotation;

import org.redrock.weixin.interceptor.BaseInterceptor;

import java.lang.annotation.*;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface Weixin {
    Class<? extends BaseInterceptor> value();
}