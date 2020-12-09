package com.yunnanhot.tacos.data;

import com.yunnanhot.tacos.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 类<code>Doc</code>用于：TODO
 *
 * @author 宇智波独孤
 * @version 1.0
 * @date 2020-12-09
 */
public interface OrderRepository extends CrudRepository<Order,Long> {
//    依据DeliveryZip来找到Order
    List<Order> findByDeliveryZip(String deliveryZip);
}

