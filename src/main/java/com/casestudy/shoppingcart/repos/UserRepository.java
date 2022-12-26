package com.casestudy.shoppingcart.repos;

import com.casestudy.shoppingcart.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select u from User u where u.userId =:id")
    User getUserById(@Param("id") int userId);

    @Modifying
    @Transactional
    @Query("update User u set u.userName=:uname,u.userPassword=:upwd,u.email=:email,u.street=:street,u.city=:city,u.state=:state,u.pinCode=:pincode where u.userId=:uid")
    int updateProfileById(@Param("uname") String userName,@Param("upwd") String userPassword,@Param("email") String email,@Param("street") String street,@Param("city") String city,@Param("state") String state,@Param("pincode") String pinCode ,@Param("uid") int userId);

    @Query(value = "select u from User u where u.userName =: uname")
    User getUserByUserName(@Param("uname") String userName);

    Optional<User> findByUserName(String userName);


}
