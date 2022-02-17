/*
 *
 *  *  *  * ---------------------------------------------------------------------------------------------
 *  *  *  *  *  Copyright (c) IJSE-intern. All rights reserved.
 *  *  *  *  *  Licensed under the MIT License. See License.txt in the project root for license information.
 *  *  *  *  --------------------------------------------------------------------------------------------/
 *
 */

package com.example.customercrud.repository;

import com.example.customercrud.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 5/28/2021
 **/

@EnableMongoRepositories
public interface UserRepo extends MongoRepository<User, String> {
    User findByEmailAndStatusIsNotIn(String email, String status);

    boolean existsByEmailEqualsAndStatusIsNotIn(String email, String status);
}
