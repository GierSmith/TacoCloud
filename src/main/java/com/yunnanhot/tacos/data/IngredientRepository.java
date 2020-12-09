package com.yunnanhot.tacos.data;


import com.yunnanhot.tacos.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient,String> {
    /*
    * CrudRepository declares about a dozen methods for CRUD (create, read, update,
delete) operations.
    *
    * 其中第一个泛型参数是被持久化的实体类型，第二个参数是实体的ID属性的类型
    * */
}
