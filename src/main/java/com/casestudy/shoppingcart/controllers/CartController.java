package com.casestudy.shoppingcart.controllers;

import com.casestudy.shoppingcart.entities.Cart;
import com.casestudy.shoppingcart.entities.CartItem;
import com.casestudy.shoppingcart.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PreAuthorize("hasAnyRole('Admin','User')")
    @GetMapping("/{userId}/getCart")
    public ResponseEntity<Cart> getCart(@PathVariable("userId") Integer userId){
        return cartService.getCartByUserId(userId);
    }

    @PreAuthorize("hasAnyRole('Admin','User')")
    @GetMapping("/{userId}/getCartItem/{cartitemId}")
    public ResponseEntity<CartItem> getCartItem(@PathVariable("userId") Integer userId, @PathVariable("cartitemId") Integer cartItemId){
        return cartService.getCartItem(cartItemId,userId);
    }



    @PreAuthorize("hasAnyRole('Admin','User')")
    @GetMapping("/{userId}/add/{productId}")
    public ResponseEntity<CartItem> addCartItem(@PathVariable("userId") Integer userId, @PathVariable("productId") Integer productId){
        System.out.println(userId);
        System.out.println(productId);
        System.out.println("calling");
        return cartService.addCartItem(userId,productId);
    }



    @PreAuthorize("hasAnyRole('Admin','User')")
    @GetMapping("/{userId}/remove/{productId}")
    public ResponseEntity<String> RemoveFromCart(@PathVariable("userId") Integer userId, @PathVariable("productId") Integer productId){
        System.out.println("item removed");
        return cartService.deleteCartItem(productId,userId);
    }



    @PreAuthorize("hasAnyRole('Admin','User')")
    @PostMapping(value= "/{userId}/changeQuantity/{productId}")
    public ResponseEntity<CartItem> changeProductQuantity(@PathVariable("userId") Integer userId, @PathVariable("productId") Integer productId, @RequestBody int quantity){
        return cartService.changeProductQuantity(productId,userId,quantity);
    }

    @PreAuthorize("hasAnyRole('Admin','User')")
    @GetMapping(value= "/{userId}/increaseQuantity/{productId}")
    public ResponseEntity<CartItem> increaseQuantity(@PathVariable("userId") Integer userId, @PathVariable("productId") Integer productId){
        return cartService.increaseProductQuantity(productId,userId);
    }


    @PreAuthorize("hasAnyRole('Admin','User')")
    @GetMapping(value= "/{userId}/decreaseQuantity/{productId}")
    public ResponseEntity<CartItem> decreaseQuantity(@PathVariable("userId") Integer userId, @PathVariable("productId") Integer productId){
        return cartService.decreaseProductQuantity(productId,userId);
    }

}
