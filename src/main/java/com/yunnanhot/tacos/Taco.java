package com.yunnanhot.tacos;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Taco {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  /*
  *因为由数据库信息自动生成对象的id所以使用@GeneratedValue(strategy= GenerationType.AUTO)
  * */
  private Long id;

  private Date createdAt;

  @ManyToMany(targetEntity=Ingredient.class)
  @Size(min=1, message="You must choose at least 1 ingredient")
  /*
  * To declare the relationship between a Taco and its associated Ingredient list,
  * you annotate ingredients with @ManyToMany.
  * A Taco can have many Ingredient objects, and an Ingredient can be a part of many Tacos.
  * */
  private List<Ingredient> ingredients;

  @NotNull//字段不能为空
  @Size(min=5, message="Name must be at least 5 characters long")
  private String name;

  @PrePersist
//在Taco对象持久化之前，我们将会使用这个方法来设置生产日期
  void createdAt() {
    this.createdAt = new Date();
  }
}
