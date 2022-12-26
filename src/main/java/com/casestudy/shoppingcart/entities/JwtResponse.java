package com.casestudy.shoppingcart.entities;

import java.util.Optional;

public class JwtResponse {

    private User user;

    private String jwtToken ;



    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public JwtResponse() {
    }
}
