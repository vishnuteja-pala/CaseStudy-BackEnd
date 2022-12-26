package com.casestudy.shoppingcart.repos;

import com.casestudy.shoppingcart.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
}
