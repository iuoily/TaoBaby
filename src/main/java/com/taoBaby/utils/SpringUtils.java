package com.taoBaby.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtils {

    private static final ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

    public static ApplicationContext getContext() {
        return context;
    }

    public static <T> T getBean(Class<T> tClass) {
        return getContext().getBean(tClass);
    }
}
