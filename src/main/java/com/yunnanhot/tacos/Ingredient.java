package com.yunnanhot.tacos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
//Data会生成缺失的，getter and setter equals(), hashCode(),toString()
//还会生成所有以final属性作为参数的构造器
@RequiredArgsConstructor
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

    //getter and setter equals(), hashCode(),toString()
}
