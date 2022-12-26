package com.casestudy.shoppingcart.controllers;

import com.casestudy.shoppingcart.entities.ImageModel;
import com.casestudy.shoppingcart.entities.Product;
import com.casestudy.shoppingcart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasRole('Admin')")
    @PostMapping(value = {"/addProduct"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Product> addProduct(@RequestPart("product") Product product, @RequestPart("imageFile")MultipartFile[] file) {
        try {
            Set<ImageModel> images = uploadImage(file);
            product.setProductImages(images);
            return new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();
        for(MultipartFile file : multipartFiles){
            ImageModel imageModel = new ImageModel(file.getOriginalFilename(), file.getContentType(),file.getBytes());
            imageModels.add(imageModel);
        }
        return imageModels;
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/deleteProduct/{id}")
    public ResponseEntity<Integer> deleteProduct(@PathVariable("id") Integer id){
        return new ResponseEntity<>(productService.deleteProduct(id),HttpStatus.OK);
    }

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
        }


    @GetMapping("/getById/{id}")
    public  ResponseEntity<Product> getProductById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
    }


    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/update")
    public int updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }


    @PreAuthorize("hasAnyRole('Admin','User')")
    @GetMapping("/{category}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable("category") String category){
        return new ResponseEntity<>(productService.getProductByCategory(category),HttpStatus.OK);

    }

    @PreAuthorize("hasAnyRole('Admin',User)")
    @GetMapping("/search/{searchString}")
    public ResponseEntity<List<Product>> getProductBySearchString(@PathVariable("searchString") String searchString){
        return new ResponseEntity<>(productService.getProductBySearchString(searchString),HttpStatus.OK);
    }

    @GetMapping("/{category}/getFilteredProducts")
    public ResponseEntity<List<Product>> getFilteredProductByCategory(@PathVariable("category") String filteredCategory ){
        return new ResponseEntity<>(productService.getFilteredProductByCategory(filteredCategory),HttpStatus.OK);
    }




}
