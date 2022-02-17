/*
 *
 *  *  *  * ---------------------------------------------------------------------------------------------
 *  *  *  *  *  Copyright (c) IJSE-intern. All rights reserved.
 *  *  *  *  *  Licensed under the MIT License. See License.txt in the project root for license information.
 *  *  *  *  --------------------------------------------------------------------------------------------/
 *
 */

package com.example.customercrud.service.impl;

import com.example.customercrud.entity.User;
import com.example.customercrud.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 5/28/2021
 **/
@Service
public class MyUserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email){
        if (userRepo.existsByEmailEqualsAndStatusIsNotIn(email, "DELETED")){
            User user = userRepo.findByEmailAndStatusIsNotIn(email, "DELETED");
            return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User is not exists");
        }
    }
}
