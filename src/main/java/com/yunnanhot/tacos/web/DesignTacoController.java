package com.yunnanhot.tacos.web;

import com.yunnanhot.tacos.Ingredient;
import com.yunnanhot.tacos.Ingredient.Type;
import com.yunnanhot.tacos.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
//@Slf4j是lombok提供的注解，在运行时会自动生成一个SLF4J (Simple Logging Facade for Java)的Logger类型的变量
@Controller
@RequestMapping("/design")
public class DesignTacoController {
/*
    @Slf4j修饰类的作用等效于在类中声明私有变量
    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(DesignTacoController.class)
* */
    @GetMapping
    public String showDesignForm(Model model) {
/*        Model is an object that ferries data between a controller and
          whatever view is charged with rendering that data.
          Ultimately, data that’s placed in Model attributes is copied
          into the servlet response attributes, where the view can find them.
  * */


        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
//                    根据配料类型过滤列表
                    filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());

   log.info("设计控制器执行完毕");
        return "design";
/*
    View libraries such as Thymeleaf are designed to be decoupled （解耦）
    from any particular（特定） web framework.

    As such, they’re unaware（不知道） of Spring’s model abstraction and are
    unable to work with the data that the controller places in Model.

    But they can work with servlet request attributes.
    Therefore, before Spring hands the request over to a view,
    it copies the model data into request attributes that
    Thymeleaf and other view templating options have ready access to.
* * */
    }

    @PostMapping
    public String processDesign(@Valid Taco design , Errors errors) {
//     在参数上使用校验注解，当校验失败后，返回设计页面
        if (errors.hasErrors()) {
            log.info("bean 验证出现问题");
            return "design";
        }
// Save the taco design...
// We'll do this in chapter 3
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
//        重定向到url--/orders/current，也就是让浏览器重新发起一个请求，请求的url为/orders/current
//        目的是当用户自定义一个taco后，会被重定向到一个订单页面，在这样创建一个订单。
    }


    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
