package com.casestudy.shoppingcart.services;


import com.casestudy.shoppingcart.entities.User;

public interface UserService {

    User newUser(User user);

    //    customer find

    User getUserById(int id,String token);

    int updateUserDetails(User user);
    void initRoleAndUser();
}
