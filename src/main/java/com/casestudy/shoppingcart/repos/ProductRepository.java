package com.casestudy.shoppingcart.repos;


import com.casestudy.shoppingcart.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface ProductRepository  extends JpaRepository<Product,Integer> {

    @Query(" select p from Product p where p.productName =:n and p.productPrice =:p and p.productDescription =:d and p.productCategory =:c and p.productSubCategory =:sc ")
    List<Product> getProductsByName(@Param("n") String productName, @Param("p") int productPrice, @Param("d") String productDescription, @Param("c") String productCategory, @Param("sc") String productSubCategory);

    @Query("select p from Product p where p.productId =:id ")
    Product getProductByProductId(@Param("id") int productId);

    @Transactional
    @Modifying
    @Query("update Product p set p.productName =:n , p.productPrice =:p , p.productDescription =:d , p.productCategory =:c , p.productSubCategory =:sc  where p.productId =:id")
    int updateProductById(@Param("n") String productName, @Param("p") int productPrice, @Param("d") String productDescription, @Param("c") String productCategory, @Param("sc") String productSubCategory, @Param("id") int id);

    @Query("select p from Product p where p.productCategory =:c")
    List<Product> getProductByCategory(@Param("c") String productCategory);

    @Query("select   p from Product p where p.productName =:p")
    List<Product> getProductBySearchString(@Param("p") String searchString);

    @Query("select p from Product p where p.productCategory =:c ")
    List<Product> getFilteredProductByCategory(@Param("c") String filteredCategory);

    @Query("select p from Product p where p.productPrice >=?1 and p.productPrice<=?2")
    List<Product> getSortedProductByPrice( int minPrice, int maxPrice);

}
