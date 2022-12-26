package com.casestudy.shoppingcart.services;

import com.casestudy.shoppingcart.entities.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);

    int deleteProduct(int id);


    List<Product> getAllProducts();

    Product getProductById(int id);

    int updateProduct(Product product);

    List<Product> getProductByCategory(String category);

    List<Product> getProductBySearchString(String searchString);

    List<Product> getFilteredProductByCategory(String filteredCategory);
}
