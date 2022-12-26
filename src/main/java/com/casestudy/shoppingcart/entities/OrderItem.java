package com.casestudy.shoppingcart.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderItemId;

    @OneToOne
    @JoinColumn(name="product_id", referencedColumnName ="productID")
    private Product product;

    private int quantity;
    @JsonIgnore
    @ManyToOne
    private CustomerOrder orders;

    public OrderItem() {
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderItem(Product product, int quantity, CustomerOrder orders) {
        this.product = product;
        this.quantity = quantity;
        this.orders = orders;
    }

    public OrderItem(int orderItemId, Product product, int quantity) {
        this.orderItemId = orderItemId;
        this.product = product;
        this.quantity = quantity;

    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", product=" + product +
                ", quantity=" + quantity +
                ", orders=" + orders +
                '}';
    }
}
