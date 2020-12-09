package com.yunnanhot.tacos.data;

import com.yunnanhot.tacos.Taco;
import org.springframework.data.repository.CrudRepository;

/**
 * 类<code>Doc</code>用于：TODO
 *
 * @author 宇智波独孤
 * @version 1.0
 * @date 2020-12-09
 */
public interface TacoRepository extends CrudRepository<Taco,Long> {//泛型参数不能是基本数据类型
}
