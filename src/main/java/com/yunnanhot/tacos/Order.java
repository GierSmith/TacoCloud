package com.yunnanhot.tacos;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class Order {

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
}
