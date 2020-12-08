package com.yunnanhot.tacos.web;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
/**
 * 实现了WebMvcConfigurer 接口，这个接口定义了多个方法来配置Spring MVC.
 * 接口中的每个方法都有默认实现
 * */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
/**
 *
 * The addViewControllers() method is given a ViewControllerRegistry that you
 * can use to register one or more view controllers.
 *
 * Here, you call addViewController()
 * on the registry, passing in "/", which is the path for which your view controller will
 * handle GET requests.
 * */
        registry.addViewController("/")  //That method returns a ViewControllerRegistration object
                .setViewName("home");
    }
}
