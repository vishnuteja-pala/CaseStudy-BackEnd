package com.casestudy.shoppingcart.services;

import com.casestudy.shoppingcart.entities.Role;
import com.casestudy.shoppingcart.repos.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role createNewRole(Role role) {
        return roleRepository.save(role) ;

    }
}
