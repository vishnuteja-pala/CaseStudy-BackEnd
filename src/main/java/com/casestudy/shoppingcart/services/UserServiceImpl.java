package com.casestudy.shoppingcart.services;

import com.casestudy.shoppingcart.Util.JwtUtil;
import com.casestudy.shoppingcart.entities.Role;
import com.casestudy.shoppingcart.entities.User;
import com.casestudy.shoppingcart.repos.RoleRepository;
import com.casestudy.shoppingcart.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements  UserService {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepo ;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder ;


    @Override
    public User getUserById(int id,String token) {
        String[] splitToken = token.split("\\s+");
        String tokenUserName = jwtUtil.getUsernameFromToken(splitToken[1]);
//      Optional<User> tokenUser = userRepo.findByUserName(tokenUserName);
      int tokenUserId = userRepo.findByUserName(tokenUserName).get().getUserId();
        if(tokenUserId == id) {
                User user = userRepo.getUserById(id);
                return user;
            }
            return null;
    }

    @Override
    public int updateUserDetails(User user) {
        if (userRepo.existsById(user.getUserId())){
           int i = userRepo.updateProfileById(user.getUserName(), user.getUserPassword(), user.getEmail(),user.getStreet(),user.getCity(),user.getState(),user.getPinCode(), user.getUserId());
            user.setUserPassword(getEncodedPassword(user.getUserPassword()));
            userRepo.save(user);
            return i;
        }
        return 0;
    }

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleRepository.save(userRole);


        User adminUser = new User();
        adminUser.setUserId(1);
        adminUser.setUserName("admin");
        String password = "admin@pass";
        adminUser.setUserPassword(getEncodedPassword(password));
        adminUser.setStreet("street1");
        adminUser.setCity("eluru");
        adminUser.setState("AP");
        adminUser.setPinCode("534007");
        adminUser.setEmail("admin123@email.com");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userRepo.save(adminUser);

        User normalUser = new User();
        normalUser.setUserId(2);
        normalUser.setUserName("vishnu");
        String pass = "vishnu@pass";
        normalUser.setUserPassword(getEncodedPassword(pass));
        normalUser.setStreet("street1");
        normalUser.setCity("eluru");
        normalUser.setState("AP");
        normalUser.setPinCode("534007");
        normalUser.setEmail("vishnu123@email.com");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        normalUser.setRole(userRoles);
        userRepo.save(normalUser);
    }

//    @Override
//    public User newUser(User user1) {
//        User user = new User();
//        Role role = roleRepository.findById(2).get();
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(role);
//        user.setRole(userRoles);
//        user.setUserPassword(getEncodedPassword(user1.getUserPassword()));
////        user.setUserPassword(user.getUserPassword());
//        user.setAddress(user1.getAddress());
//        user.setUserName(user1.getUserName());
//        user.setEmail(user1.getEmail());
//        user = userRepo.save(user);
//        return userRepo.saveAndFlush(user)  ;
//    }
    public User newUser(User user){
            Role role = roleRepository.findById(2).get();
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);
            user.setUserPassword(getEncodedPassword(user.getUserPassword()));
            return userRepo.save(user);
    }



    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
