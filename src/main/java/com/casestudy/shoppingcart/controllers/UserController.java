package com.casestudy.shoppingcart.controllers;

import com.casestudy.shoppingcart.entities.User;
import com.casestudy.shoppingcart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }


    @SuppressWarnings("SpellCheckingInspection")
    @PostMapping("/signup")
    public User addUser(@RequestBody User user ){
        return  userService.newUser(user);
    }

    @SuppressWarnings("SpellCheckingInspection")
    @PreAuthorize("hasAnyRole('Admin','User')")
    @GetMapping("/getprofile/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") int userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin','User')")
    @PostMapping("/updateProfile")
    public ResponseEntity<Integer> updateProfileByUserId(@RequestBody User user){
        return new ResponseEntity<>(userService.updateUserDetails(user),HttpStatus.OK);
    }


    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

}

