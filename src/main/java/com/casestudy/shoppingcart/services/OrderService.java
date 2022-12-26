package com.casestudy.shoppingcart.services;

import com.casestudy.shoppingcart.entities.CustomerOrder;
import com.casestudy.shoppingcart.entities.OrderItem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    ResponseEntity<List<CustomerOrder>> createOrder(int userId);

    ResponseEntity<List<CustomerOrder>> getOrders(int userId);

//    ResponseEntity<List<OrderItem>> getOrderItemsByOrderId(int orderId);

}
