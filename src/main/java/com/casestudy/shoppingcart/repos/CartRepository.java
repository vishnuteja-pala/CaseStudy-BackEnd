package com.casestudy.shoppingcart.repos;

import com.casestudy.shoppingcart.entities.Cart;
import com.casestudy.shoppingcart.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@EnableJpaRepositories
public interface CartRepository extends JpaRepository<Cart, Integer> {

    // get cart using userId



    @Query(value = "Select c from Cart c where c.user.userId=:uid")
     Cart getCartByUserId(@Param("uid") int userId);




    //  get cart item using userId and cartItemId



    @Query(value = "Select ci from CartItem  ci where ci.cartItemId=:ciId  and ci.cart.user.userId=:uid")
    public CartItem getCartItemByCartItemId(@Param("ciId") int cid, @Param("uid") int uid);




    // ------------ get cart item using userId and productId-------------------
    //check if cart item already exists
    @Query(value = "Select ci from CartItem ci where ci.product.productId=:pid and ci.cart.user.userId=:uid")
    public CartItem cartItemExists(@Param("pid") int productId, @Param("uid") int userId);




    //if exists increase the quantity
    @Modifying
    @Transactional
    @Query(value = "Update cart_item  Set quantity=:q where product_Id=:pid and cart_cart_id=(select cart_id from cart where userid =:uid)", nativeQuery = true)
    int updateQuantity(@Param("q") int quantity, @Param("pid") int productId, @Param("uid") int userId);



    @Modifying
    @Transactional
    @Query(value = "Update cart_item  Set quantity=:q where product_Id=:pid and cart_cart_id=(select cart_id from cart where userid =:uid)", nativeQuery = true)
    int increaseQuantityByOne(@Param("q") int quantity, @Param("pid") int productId, @Param("uid") int userId);



    @Modifying
    @Transactional
    @Query(value = "Update cart_item  Set quantity=:q where product_Id=:pid and cart_cart_id=(select cart_id from cart where userid =:uid)", nativeQuery = true)
    int decreaseQuantityByOne(@Param("q") int quantity, @Param("pid") int productId, @Param("uid") int userId);



    @Modifying
    @Transactional
    @Query(value="DELETE from cart_item  where product_Id=:pid and cart_cart_id=(select cart_id from cart where userid =:uid)", nativeQuery = true)
    int removeItemFromCart(@Param("pid") int productId, @Param("uid") int userId);
}
