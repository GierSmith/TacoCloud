package com.yunnanhot.tacos.web;

import com.yunnanhot.tacos.Order;
import com.yunnanhot.tacos.data.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
//在运行期间创建 SLF4J Logger 对象，使用这个对象来记录订单详细
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String orderFrom(Model model){
        model.addAttribute("order",new Order());
        return "orderForm";
    }

    /**
     * @param order
     * @param errors
     * @param sessionStatus  会话状态，当处理完成之后设置会话状态为已完成
     * @return
     */
    @PostMapping
    public String processOrder(@Valid  Order order , Errors errors, SessionStatus sessionStatus){
        if(errors.hasErrors()){
            return "orderForm";
        }
         log.info("Order submitted:"+order);


        orderRepo.save(order);
        sessionStatus.setComplete();

         return "redirect:/" ;
    }
}
