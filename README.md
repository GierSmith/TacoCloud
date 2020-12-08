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
