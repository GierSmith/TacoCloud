package com.yunnanhot.tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*
@SpringBootApplication 注解是以下3个注解的组合，也就是除了Java内置的元注解之外还被这三个注解修饰

1. @SpringBootConfiguration ：
    被这个注解修饰的类将视为一个配置类。这个注解是@Configuration的特殊形式，源码中这个注解被@Configuration修饰

2.@EnableAutoConfiguration
    启用Spring Boot 的自动化配置。

3.@ComponentScan
    启用组件扫描
*/
public class TacosApplication {

//        因为是打包为jar包，这是运行这个jar文件时要执行的方法。(程序的入口 ？)
    public static void main(String[] args) {
/* 这个run方法会真正的执行应用的引导过程，创建 Spring application context. （创建Spring 容器 ？）

  创建容器需要指导容器的配置文件，因为采用Java配置，所以这个run方法的参数就是一个用来配置容器的配置类 ？
  配置类可以有多个，所以这个run方法还有一个重载版本，接收一个class对象数组和一个参数列表。
 */
        SpringApplication.run(TacosApplication.class, args);
    }

}
