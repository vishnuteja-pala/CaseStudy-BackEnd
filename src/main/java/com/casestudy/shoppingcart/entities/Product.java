package com.casestudy.shoppingcart.entities;


import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;
    @NonNull
    private String productName;
    @NonNull
    private int productPrice;
    @NonNull
    @Column(length = 2000)
    private String productDescription;
    @NonNull
    private String productCategory;
    @NonNull
    private String productSubCategory;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "product_images",
            joinColumns = {
            @JoinColumn(name = "product_id")
            },
            inverseJoinColumns = {
               @JoinColumn(name = "image_id")
    }
    )
    private Set<ImageModel> productImages;

//    @ManyToOne
//    private Order orderItem;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @NonNull
    public String getProductName() {
        return productName;
    }

    public void setProductName(@NonNull String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    @NonNull
    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(@NonNull String productDescription) {
        this.productDescription = productDescription;
    }

    @NonNull
    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(@NonNull String productCategory) {
        this.productCategory = productCategory;
    }

    @NonNull
    public String getProductSubCategory() {
        return productSubCategory;
    }

    public void setProductSubCategory(@NonNull String productSubCategory) {
        this.productSubCategory = productSubCategory;
    }

    public Set<ImageModel> getProductImages() {
        return productImages;
    }

    public void setProductImages(Set<ImageModel> productImages) {
        this.productImages = productImages;
    }

    public Product() {
    }

    public Product(int productId, @NonNull String productName, int productPrice, @NonNull String productDescription, @NonNull String productCategory, @NonNull String productSubCategory) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productCategory = productCategory;
        this.productSubCategory = productSubCategory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productDescription='" + productDescription + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", productSubCategory='" + productSubCategory + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productName.equals(product.productName) && productDescription.equals(product.productDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productDescription);
    }
}
