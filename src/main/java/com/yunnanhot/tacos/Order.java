package com.yunnanhot.tacos;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Table(name="Taco_Order")
//这表明Order实体应该持久化到数据库中名为Taco_Order的表中
//JPA默认会将实体持久化到Oeder的表中。
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Date placedAt;

    @NotBlank(message = "Name is required")//message is error tip
    private String name;

    @NotBlank(message="Street is required")
    private String street;

    @NotBlank(message="City is required")
    private String city;

    @NotBlank(message="State is required")
    private String state;

    @NotBlank(message="Zip code is required")
    private String zip;

    @CreditCardNumber(message="Not a valid credit card number")//这个注解声明表明该值必须是合法的信用卡号
//    This annotation declares that the property’s value must be a valid credit card number that
//    passes the Luhn algorithm check
//    Luhn 算法是 https://en.wikipedia.org/wiki/Luhn_algorithm
    private String ccNumber;//要保证不能为null,还要是一个合法的信用卡号码。

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")//包含3个数字
    private String ccCVV;


    @ManyToMany(targetEntity=Taco.class)
    private List<Taco> tacos = new ArrayList<>();


    public void addDesign(Taco design) {
        this.tacos.add(design);
    }
    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }
}
