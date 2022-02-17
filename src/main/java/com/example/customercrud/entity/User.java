/*
 *
 *  *  *  * ---------------------------------------------------------------------------------------------
 *  *  *  *  *  Copyright (c) IJSE-intern. All rights reserved.
 *  *  *  *  *  Licensed under the MIT License. See License.txt in the project root for license information.
 *  *  *  *  --------------------------------------------------------------------------------------------/
 *
 */

package com.example.customercrud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 5/28/2021
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data

@Document(collection = "User")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String companyId;
    private String avatar;
    private String registerDate;
    private Object special;
    private String userType;
    private String[] accessPoint;
    private String status;
}
