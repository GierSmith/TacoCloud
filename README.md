# TacoCloud
## A Spring in Action 5 a Dome
 Projects for Learning Spring Boot

这个分支是使用的Spring-Data-JPA 作为持久化方案
````xml
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
````
注意spring-boot-starter-data-jpa 不仅会导入Spring Data JPA
还会传递性的将Hibernate作为JPA实现导入

如果想使用其他的JPA实现，那么首先要将Hibernate的实现
排除出去，将所选择的实现包含进来，比如说使用EclipseLink 来替换 Hibernate的实现

那么应该使用的坐标是

````xml
<dependencys>
........

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
    <exclusions>
        <exclusion>
            <artifactId>hibernate-entitymanager</artifactId>
            <groupId>org.hibernate</groupId>
        </exclusion>
    </exclusions>
</dependency>

<dependency>
    <groupId>org.eclipse.persistence</groupId>
    <artifactId>eclipselink</artifactId>
    <version>2.5.2</version>

</dependency>
........
</dependencys>
````
在使用Spring Data JPA 的时候，持久化的逻辑是通过接口中的方法
签名来体现的。对于基本的CRUD 只需声明实体相关的持久化接口
```java
public interface OrderRepository extends 
                         CrudRepository<Order,Long> {
    List<Order> findByDeliveryZip(String deliveryZip);

}
```

接口的实现类以及具体的实现方法将会在运行时生成，只需要在
控制器中注入就可以使用这个接口了。
如果不满足于CrudRepository 接口所提供的基本持久化操作，可以在接口中声明其他方法
比如
```java
public interface OrderRepository extends CrudRepository<Order,Long> {
//    依据DeliveryZip来找到Order
    List<Order> findByDeliveryZip(String deliveryZip);
}
```
When generating the repository implementation, Spring Data examines any methods
in the repository interface, parses the method name, and attempts to understand the
method’s purpose in the context of the persisted object (an Order, in this case). 

In essence, Spring Data defines a sort of miniature domain-specific language (DSL)
where persistence details are expressed in repository method signatures.

也就是说通过定义方法名称来指导Spring Data JPA 完成持久化操作
那么这些接口中定义的方法名称就要满足一些约定，如果通过方法名描述
是困难甚至不可能的时候，在方法上使用@Query( sql )

to explicitly specify the query to be performed when the
method is called, as this example shows:

```java
@Query("Order o where o.deliveryCity='Seattle'")
List<Order> readOrdersDeliveredInSeattle();
```
