package com.yunnanhot.tacos.web;

import com.yunnanhot.tacos.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
//在运行期间创建 SLF4J Logger 对象，使用这个对象来记录订单详细
@Controller
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/current")
    public String orderFrom(Model model){
        model.addAttribute("order",new Order());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid  Order order , Errors errors){
        if(errors.hasErrors()){
            return "orderForm";
        }
         log.info("Order submitted:"+order);

         return "redirect:/" ;
    }
}
