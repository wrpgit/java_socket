package com.wrpxcx.util;

import com.wrpxcx.mapper.UserMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.DelegatingServletInputStream;

/**
 * @author: wrp
 * @TODO: 加载spring配置文件，获取bean
 * @time: 2020-05-26 09:35
 **/
public class SpringUtil {
    private static ApplicationContext context;

    public static Object getBean(String beanName){
        if(context == null) {
            context = new ClassPathXmlApplicationContext("applicationContext.xml");
            return context.getBean(beanName);
        }
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        else
            return context.getBean(beanName);
    }
}
