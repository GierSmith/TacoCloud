package com.yunnanhot.tacos.web;

import com.yunnanhot.tacos.Ingredient;
import com.yunnanhot.tacos.Ingredient.Type;
import com.yunnanhot.tacos.Order;
import com.yunnanhot.tacos.Taco;
import com.yunnanhot.tacos.data.IngredientRepository;
import com.yunnanhot.tacos.data.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
//@Slf4j是lombok提供的注解，在运行时会自动生成一个SLF4J (Simple Logging Facade for Java)的Logger类型的变量
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
/**
 * 类级的@SessionAttributes注解指定了一个模型对象，如order属性，
 * 这个对象应该保存在会话中，并在多个请求中可用。
 * */
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    private TacoRepository designRepo;

    @Autowired
    public DesignTacoController( IngredientRepository ingredientRepo, TacoRepository designRepo) {
        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
    }

/***  @ModelAttribute annotation on order() ensures that an Order object will be created in the model
*你需要订单在多个请求中存在，这样你就可以创建多个taco并将它们添加到订单中。
 */
    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }



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

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

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


    /**
     * @param design  表单中的数据，经过校验后的对象
     * @param errors  可以使用该对象来判断，表单中的数据是否满足Bean验证要求
     * @param order   The Order parameter is annotated with @ModelAttribute to indicate (表明) that its
     * value should come from the model and that Spring MVC shouldn’t attempt to bind
     * request parameters to it.
     * @return
     */
    @PostMapping
    public String processDesign(@Valid Taco design , Errors errors,@ModelAttribute Order order) {
//     在参数上使用校验注解，当校验失败后，返回设计页面
        if (errors.hasErrors()) {
            log.info("bean 验证出现问题");
            return "design";
        }

        Taco saved = designRepo.save(design);
        order.addDesign(saved);

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
