package com.casestudy.shoppingcart.services;

import com.casestudy.shoppingcart.entities.Cart;
import com.casestudy.shoppingcart.entities.CartItem;
import com.casestudy.shoppingcart.entities.Product;
import com.casestudy.shoppingcart.entities.User;
import com.casestudy.shoppingcart.repos.CartItemRepository;
import com.casestudy.shoppingcart.repos.CartRepository;
import com.casestudy.shoppingcart.repos.ProductRepository;
import com.casestudy.shoppingcart.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;


@Service
    public class CartServiceImpl implements CartService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;


    @Override
    public ResponseEntity<Cart> getCartByUserId(int userId) {
        Cart cart = cartRepository.getCartByUserId(userId);
        if (cart == null) {
            User u = userRepository.getUserById(userId);
            cartRepository.save(new Cart(u));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Cart c = new Cart(cart.getCartId(), cart.getCartItem());
            return new ResponseEntity<>(c, HttpStatus.OK);
        }
    }


    @Override
    public ResponseEntity<CartItem> getCartItem(int cartItemId, int userId) {
        CartItem cartItem = cartRepository.getCartItemByCartItemId(cartItemId, userId);
        if (cartItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(cartItem, HttpStatus.OK);
        }
    }


    @Override
    public ResponseEntity<CartItem> addCartItem(int userId, int productId) {
        CartItem ci = cartRepository.cartItemExists(productId, userId);
        if (ci == null) {
            Product pro = productRepository.getProductByProductId(productId);
            Cart cart = cartRepository.getCartByUserId(userId);
            CartItem newCartItem = new CartItem(cart, 1, pro);
            cartItemRepository.save(newCartItem);
            return new ResponseEntity<>(newCartItem, HttpStatus.CREATED);
        } else {
            int result = cartRepository.updateQuantity((ci.getQuantity() + 1), productId, userId);
            CartItem cartItem = cartRepository.cartItemExists(productId, userId);
            return new ResponseEntity<>(cartItem, HttpStatus.OK);
        }


    }


    @Override
    public ResponseEntity<String> deleteCartItem(int productId, int userId) {
        CartItem ci = cartRepository.cartItemExists(productId, userId);
        if (ci == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            int result = cartRepository.removeItemFromCart(productId, userId);
            return new ResponseEntity<>("cartItem with id: " + ci.getCartItemId() + " is removed", HttpStatus.OK);
        }
    }


    @Override
    public ResponseEntity<CartItem> changeProductQuantity(int productId, int userId, int quantity) {
        CartItem ci = cartRepository.cartItemExists(productId, userId);
        if (ci == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            int result = cartRepository.updateQuantity(quantity, productId, userId);
            CartItem cartItem = cartRepository.cartItemExists(productId, userId);
            return new ResponseEntity<>(cartItem, HttpStatus.OK);
        }
    }


    @Override
    public ResponseEntity<CartItem> increaseProductQuantity(int productId, int userId) {
        CartItem ci = cartRepository.cartItemExists(productId, userId);
        if (ci == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            int result = cartRepository.increaseQuantityByOne(ci.getQuantity() + 1, productId, userId);
            CartItem cartItem = cartRepository.cartItemExists(productId, userId);
            return new ResponseEntity<>(cartItem, HttpStatus.OK);
        }


    }


    @Override
    public ResponseEntity<CartItem> decreaseProductQuantity(int productId, int userId) {
        CartItem ci = cartRepository.cartItemExists(productId, userId);
        if (ci == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            int quantity = ci.getQuantity();
            if (quantity == 1) {
                int result = cartRepository.removeItemFromCart(productId, userId);
                return new ResponseEntity<>(HttpStatus.OK);
            } else if (quantity == 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                int result = cartRepository.decreaseQuantityByOne(quantity - 1, productId, userId);
                CartItem cartItem = cartRepository.cartItemExists(productId, userId);
                return new ResponseEntity<>(cartItem, HttpStatus.OK);
            }
        }


    }
}


