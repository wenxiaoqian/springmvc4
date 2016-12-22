package com.welab.mvc.config;

import ch.qos.logback.ext.spring.web.LogbackConfigListener;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author xiaoqian.wen
 * @create 2016-12-22 11:13
 **/
public class CommonInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {

        //配置日志
        servletContext.setInitParameter("logbackConfigLocation","classpath:logback.xml");
        servletContext.addListener(LogbackConfigListener.class);


    }

}
