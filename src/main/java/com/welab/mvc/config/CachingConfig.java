package com.welab.mvc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.lang.reflect.Method;

/**
 * 缓存配置
 *
 * @author xiaoqian.wen
 * @create 2016-12-22 11:32
 **/
@Configuration
@EnableCaching
public class CachingConfig {

    private static Logger logger = LoggerFactory.getLogger(CachingConfig.class);

    @Bean
    public KeyGenerator wiselyKeyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean cacheFactory = new EhCacheManagerFactoryBean();
        cacheFactory.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return cacheFactory;
    }

    @Bean
    public CacheManager cacheManager(){
        logger.info("EhCacheCacheManager...");
        EhCacheCacheManager cacheManager = new EhCacheCacheManager();
        cacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());

        return cacheManager;
    }
}
