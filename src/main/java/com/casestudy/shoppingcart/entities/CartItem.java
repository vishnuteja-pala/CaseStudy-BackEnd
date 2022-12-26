package com.casestudy.shoppingcart.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartItemId;
    private int Quantity;


    @OneToOne
    @JoinColumn(name = "productId",referencedColumnName = "productId")
    private Product product;

    @JsonIgnore
    @ManyToOne
    private Cart cart;

    public CartItem() {
        super();
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public CartItem(int cartItemId, int quantity, Product product) {
        super();
        this.cartItemId = cartItemId;
        Quantity = quantity;
        this.product = product;
    }
    public CartItem(Cart cart, int quantity, Product product) {
        super();
        this.cart = cart;
        Quantity = quantity;
        this.product = product;

    }


    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId=" + cartItemId +
                ", Quantity=" + Quantity +
                ", product=" + product +
                '}';
    }
}
