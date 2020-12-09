package com.yunnanhot.tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;
import javax.swing.*;

/**
 * 类<code>Doc</code>用于：
 *配置Spring Security
 *
 * @author 宇智波独孤
 * @version 1.0
 * @date 2020-12-09
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    /**安全框架支持的用户存储有以下几种方案
     * An in-memory user store
     * A JDBC-based user store
     * An LDAP-backed user store
     * A custom user details service
     *
     * No matter which user store you choose, you can configure it by overriding a configure()
     * method defined in the WebSecurityConfigurerAdapter configuration base class.
     *
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
//    在方法中声明在认证过程中如何查找用户
        /*
        * One place where user information can be kept is in memory.
        *  Suppose you have only a handful of users, none of which are likely to change.
        * In that case, it may be simple enough to define those users as part of the security configuration.
        *
        * 应该是测试安全逻辑的时候才需要，在内存中配置用户
        * */

//---------------------------------------在内存中配置两个用户 begin-----------------------------------------------------------------------------
        /*

        auth
                .inMemoryAuthentication()
                .withUser("buzz")//用户名
                .password("infinity")//密码
                .authorities("ROLE_USER")//授予的权限，一个用户可以有多个权限
                .and()
                .withUser("woody")
                .password("bullseye")
                .authorities("ROLE_USER");
        */
// -------------------------------------在内存中配置两个用户 end-----------------------------------------------------------------

// -------------------------------------基于JDBC使用后端数据库的用户存储Begin-----------------------------------------------------------------
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)//设置数据源，以知道查找数据库
//        Seping Security 对所使用的数据库模式有一个默认的假设。

//                我们可以覆盖这个默认的假设
//        When replacing the default SQL queries with those of your own design,
//        it’s important to adhere to the basic contract of the queries.
//        当用自己设计的SQL查询替换默认的SQL查询时，一定要遵守查询的基本契约。
//
//        All of them take the username as their only parameter.
//        所有的查询都以用户名作为唯一参数。
//
//        The authentication query selects the username, password, and enabled status.
//        认证查询（authentication query）选择用户名、密码和 启用状态。
//
//        The authorities query selects zero or more rows containing the username and a granted authority.
//        权限查询（authentication query）选择零条或多条包含用户名和授权的记录。
//        The group authorities query selects zero or more rows, each with a group ID, a group name, and an authority.
//        组权限查询选择零条或多条记录，每条记录都有一个组ID、一个组名和一个权限。
                .usersByUsernameQuery(
                "select username, password, enabled from Users " +
                        "where username=?")
                .authoritiesByUsernameQuery(
                        "select username, authority from UserAuthorities " +
                                "where username=?")
//               用户登录时输入的密码会输入到和注册时相同的算法中进行编码，然后与数据库中存储的密文进行比较，
//                这个比较也是在PasswordEncoder接口的实现类中定义的
//               下列方法接收一个PasswordEncoder接口的实现，SPringl security框架内置了一些实现
                .passwordEncoder(new StandardPasswordEncoder("53cr3t"));


//-------------------------------------基于JDBC使用后端数据库的用户存储end-----------------------------------------------------------------

    }
}
