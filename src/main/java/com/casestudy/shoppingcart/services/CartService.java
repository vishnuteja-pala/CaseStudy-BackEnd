package com.casestudy.shoppingcart.services;

import com.casestudy.shoppingcart.entities.Cart;
import com.casestudy.shoppingcart.entities.CartItem;
import org.springframework.http.ResponseEntity;



public interface CartService {

    ResponseEntity<Cart> getCartByUserId(int userId);

    ResponseEntity<CartItem> getCartItem(int cartItemId, int userId);
    ResponseEntity<CartItem> addCartItem( int userId,int productId);

    ResponseEntity<String> deleteCartItem(int productId, int userId);

    ResponseEntity<CartItem> changeProductQuantity(int productId, int userId, int quantity);
    ResponseEntity<CartItem> increaseProductQuantity(int productId, int userId);
    ResponseEntity<CartItem> decreaseProductQuantity(int productId, int userId);
}
