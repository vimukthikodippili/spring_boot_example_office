/*
 *
 *  *  * ---------------------------------------------------------------------------------------------
 *  *  *  *  Copyright (c) IJSE-intern. All rights reserved.
 *  *  *  *  Licensed under the MIT License. See License.txt in the project root for license information.
 *  *  *  --------------------------------------------------------------------------------------------/
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
 * @since : 5/27/2021
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data

@Document(collection = "Customer")
public class Customer implements SuperEntity{
    @Id
    private String customerId;
    private String customerName;
    private String customerInitialName;
    private String companyId;
    private String customerNic;
    private String gender;
    private String city;
    private String address;
    private String mobileNumber;
    private String landPhoneNumber;
    private String email;
    private String nicPicFront;
    private String nicPicBack;
    private String status;
}
