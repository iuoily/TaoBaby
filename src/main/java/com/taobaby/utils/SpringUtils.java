package com.taobaby.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtils {

    public static ApplicationContext getContext() {
        return new ClassPathXmlApplicationContext("ApplicationContext.xml");
    }

    public static <T> T getBean(Class<T> tClass) {
        return getContext().getBean(tClass);
    }
}
