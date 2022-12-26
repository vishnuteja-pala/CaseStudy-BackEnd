package com.casestudy.shoppingcart.services;

import com.casestudy.shoppingcart.entities.Product;
import com.casestudy.shoppingcart.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements  ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product addProduct(Product product) {
        List<Product> alreadyExistsList = productRepository.getProductsByName(product.getProductName(), product.getProductPrice(), product.getProductDescription(), product.getProductCategory(), product.getProductSubCategory());


        if (alreadyExistsList == null || alreadyExistsList.isEmpty()) {
            System.out.println("empty list");
            productRepository.save(product);
            return null;
        } else {
            for (Product product1 : alreadyExistsList) {
                if (product1.equals(product)) {
                    System.out.println("Already exists");
                    return null;
                }
            }
            productRepository.save(product);
            return product;
        }
    }

    @Override
    public int deleteProduct(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return id;
        }
        return 0;
    }

    @Override
    public List<Product> getAllProducts(){
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        if
        (productRepository.existsById(id)){
            Product product = productRepository.getProductByProductId(id);
            return product;
        }
        return null;
    }

    @Override
    public int updateProduct(Product product) {
        if (productRepository.existsById(product.getProductId())){
            int i = productRepository.updateProductById(product.getProductName(),product.getProductPrice(),product.getProductDescription(), product.getProductCategory(), product.getProductSubCategory(), product.getProductId());
            return i ;
        }
        return 0;
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        List<Product> productList = productRepository.getProductByCategory(category);
        if(productList == null || productList.isEmpty()){
            return null;
        }
        else {
            return productList;
        }

    }

    @Override
    public List<Product> getProductBySearchString(String searchString) {
        List<Product> productList = productRepository.getProductBySearchString(searchString);

        if(productList == null || productList.isEmpty()){
            return null;
        }
        else {
            return productList;
        }
    }

    @Override
    public List<Product> getFilteredProductByCategory(String filteredCategory) {
        List<Product> productList = productRepository.getFilteredProductByCategory(filteredCategory);

        if(productList == null || productList.isEmpty()){
            return null;
        }
        else {
            return productList;
        }
    }


}

