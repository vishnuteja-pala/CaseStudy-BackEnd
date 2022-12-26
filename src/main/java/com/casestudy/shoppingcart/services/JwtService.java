package com.casestudy.shoppingcart.services;

import com.casestudy.shoppingcart.Util.JwtUtil;
import com.casestudy.shoppingcart.entities.JwtRequest;
import com.casestudy.shoppingcart.entities.JwtResponse;
import com.casestudy.shoppingcart.entities.User;
import com.casestudy.shoppingcart.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService  implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil ;
    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;


//    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
//        String userName = jwtRequest.getUserName();
//        String userPassword = jwtRequest.getUserPassword();
//        authenticate(userName, userPassword);
//
//        UserDetails userDetails = loadUserByUsername(userName);
//        String newGeneratedToken = jwtUtil.generateToken(userDetails);
//
//        User user = userRepository.findById(userName).get();
//        return new JwtResponse(user, newGeneratedToken);
//    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName).get();
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername() ,
                    user.getUserPassword(),
                    user.getAuthorities()
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + userName);
        }

    }


//    private Set<SimpleGrantedAuthority> getAuthority(User user) {
//        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        user.getRole().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
//        });
//        return authorities;
//    }
//
//    private void authenticate(String userName, String userPassword) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
//        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED", e);
//        } catch (BadCredentialsException e) {
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
//    }

}
