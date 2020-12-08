package com.yunnanhot.tacos.data;

import com.yunnanhot.tacos.Order;

public interface OrderRepository {
    Order save(Order order);
}
