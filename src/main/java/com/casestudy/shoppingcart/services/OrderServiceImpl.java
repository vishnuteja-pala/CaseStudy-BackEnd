package com.casestudy.shoppingcart.services;

import com.casestudy.shoppingcart.entities.Cart;
import com.casestudy.shoppingcart.entities.CartItem;
import com.casestudy.shoppingcart.entities.CustomerOrder;
import com.casestudy.shoppingcart.entities.OrderItem;
import com.casestudy.shoppingcart.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;



    @Override
    public ResponseEntity<List<CustomerOrder>> createOrder(int userId) {
        Cart cart = cartRepository.getCartByUserId(userId);
        List<CartItem> cartItems = cart.getCartItem();
        if(cartItems.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        else {
                CustomerOrder activeOrder = new CustomerOrder(userRepository.getUserById(userId), "active");

                orderRepository.save(activeOrder);
                List<OrderItem> orderItemsList = new ArrayList<>();

                for (CartItem ci : cartItems) {
                    OrderItem orderItem = new OrderItem(ci.getProduct(), ci.getQuantity(), activeOrder);
                    orderItemRepository.save(orderItem);
                    orderItemsList.add(orderItem);
                }
            System.out.println(orderItemsList);
                activeOrder.setOrderItems(orderItemsList);
                int orderUpdated = orderRepository.updateStatusOfOrder("complete",activeOrder.getOrderId());
                int clearCart = cartItemRepository.ClearCartItems(cart.getCartId());
               List<CustomerOrder> order = orderRepository.getOrdersByUserId(userId,"complete");
                return new ResponseEntity<>(order, HttpStatus.OK);

        }

    }

    @Override
    public ResponseEntity<List<CustomerOrder>> getOrders(int userId) {
        Cart cart = cartRepository.getCartByUserId(userId);
        List<CartItem> cartItems = cart.getCartItem();
        List<CustomerOrder> customerOrderList = orderRepository.getOrdersByUserId(userId,"complete");
        return new  ResponseEntity<>(customerOrderList,HttpStatus.OK);

    }

//    @Override
//    public ResponseEntity<List<OrderItem>> getOrderItemsByOrderId(int orderId) {
//        List<OrderItem> orderItems = orderRepository.getCompletedOrderByOrderId(orderId);
//        return new ResponseEntity<>(orderItems,HttpStatus.OK);
//    }
}
