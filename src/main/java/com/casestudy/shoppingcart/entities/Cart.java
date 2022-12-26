package com.casestudy.shoppingcart.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @OneToMany(mappedBy = "cart" , fetch = FetchType.EAGER,cascade = CascadeType.ALL,targetEntity = CartItem.class)
    private List<CartItem> cartItem;

    @OneToOne
    @JoinColumn(name="userID")
    private User user;

    public Cart() {
        super();
    }

    public Cart(User user ){
        super();
        this.user =user;
    }


    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public List<CartItem> getCartItem() {
        return cartItem;
    }

    public void setCartItem(List<CartItem> cartItem) {
        this.cartItem = cartItem;
    }

    public Cart(int cartId, List<CartItem> cartItem) {
        super();
        this.cartId = cartId;
        this.cartItem = cartItem;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", cartItem=" + cartItem +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return cartId == cart.cartId;
    }


}
