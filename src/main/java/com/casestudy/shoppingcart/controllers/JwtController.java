package com.casestudy.shoppingcart.controllers;

import com.casestudy.shoppingcart.Util.JwtUtil;
import com.casestudy.shoppingcart.entities.JwtRequest;
import com.casestudy.shoppingcart.entities.JwtResponse;
import com.casestudy.shoppingcart.entities.User;
import com.casestudy.shoppingcart.repos.UserRepository;
import com.casestudy.shoppingcart.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
public class JwtController {
    @Lazy
    @Autowired
    private JwtService jwtService ;

    @Lazy
    @Autowired
    private JwtUtil jwtUtil;

    @Lazy
    @Autowired
    private UserDetailsService userDetailsService;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

//    @PostMapping({"/authenticate"})
//    public ResponseEntity<JwtResponse> createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
//            this.authenticate(jwtRequest.getUserName(),jwtRequest.getUserPassword());
//        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUserName());
//        String token = this.jwtUtil.generateToken(userDetails);
//        JwtResponse jwtResponse = new JwtResponse();
//        User user = userRepository.findByUserName(jwtRequest.getUserName()).get();
//        jwtResponse.setJwtToken(token);
//        jwtResponse.setUser(user);
//      return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
//    }

    @PostMapping({"/authenticate"})
    public ResponseEntity<JwtResponse> createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        jwtRequest.getUserName(),
                        jwtRequest.getUserPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtUtil.generateToken(authentication);
        JwtResponse jwtResponse = new JwtResponse();
        User user = userRepository.findByUserName(jwtRequest.getUserName()).get();
        jwtResponse.setJwtToken(token);
        jwtResponse.setUser(user);
        return ResponseEntity.ok(jwtResponse);
    }

//    private void authenticate(String userName, String userPassword) throws Exception {
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName,userPassword);
//        try {
//            authenticationManager.authenticate(authenticationToken);
//        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED", e);
//        } catch (BadCredentialsException e) {
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
//    }


}
