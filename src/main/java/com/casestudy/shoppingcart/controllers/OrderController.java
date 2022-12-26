package com.casestudy.shoppingcart.controllers;


import com.casestudy.shoppingcart.entities.CustomerOrder;
import com.casestudy.shoppingcart.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAnyRole('Admin','User')")
    @GetMapping("/{userId}/createOrder")
    public ResponseEntity<List<CustomerOrder>> createOrder(@PathVariable("userId") Integer userId){
        return orderService.createOrder(userId);
    }

    @PreAuthorize("hasAnyRole('Admin','User')")
    @GetMapping("/{userId}/getOrders")
    public  ResponseEntity<List<CustomerOrder>> getOrders(@PathVariable("userId") Integer userId){
        return orderService.getOrders(userId);
    }

//    @GetMapping("/orderDetails/{orderId}")
//    public ResponseEntity<List<OrderItem>> getOrderItemsByOrderId(@PathVariable("orderId") Integer orderId){
//        return orderService.getOrderItemsByOrderId(orderId);
//    }
}
