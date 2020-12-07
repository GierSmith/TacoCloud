package com.yunnanhot.tacos;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//让组件扫描将这个类识别为一个组件，创建该类的一个对象，将这个对象添加到 Spring application context.（容器中）
//@Component, @Service, and @Repository 功能是一样的，但这个根据有描述性。

public class HomeController {
//    对这个路径“/”的get 请求，将会交由这个方法处理。
    @GetMapping("/")
    public String home(){

        return "home" ;
         /*
        返回逻辑视图名
        视图名究竟对应哪个视图是由多个因素决定，现在类路径下只有一个Thymeleaf 所以使用
        Thymeleaf 来定义模板。
         */
    }
}
