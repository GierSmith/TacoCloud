package com.yunnanhot.tacos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
/*JPA需要实体有一个无参构造器，lombok可以帮助我们实现这一点，上述参数将这个无参构造函数设置为私有的
*And because there are final properties that must be set,
*you also set the force attribute to true, which results in the Lombok-generated constructor
* setting them to null.
* Data注解会为我们生成一个有参构造器，但是因为使用了@NoArgsConstructor 所以由Data注解生成的有参构造器就会被移除
*显示添加RequiredArgsConstructor确保除了私有的无参构造，还有一个有参构造器
*/
@Entity//In order to declare this as a JPA entity
public class Ingredient {
//    Taco的配料

    @Id
    private final String id;//而它的id属性必须用@Id来注解，以指定它是唯一标识数据库中的实体。
    private final String name;
    private final Type type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }


}
