/*
 *
 *  *  * ---------------------------------------------------------------------------------------------
 *  *  *  *  Copyright (c) IJSE-intern. All rights reserved.
 *  *  *  *  Licensed under the MIT License. See License.txt in the project root for license information.
 *  *  *  --------------------------------------------------------------------------------------------/
 *
 */

package com.example.customercrud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 5/27/2021
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDTO implements SuperDTO{
//    @Pattern(
//            regexp = "^^[A-Z]{3}[-]CUS[0-9]{4}$",
//            message = "Customer id is not valid pleas flow the this format ex:- CUS####")
    private String customerId;
    @NotEmpty(message = "Customer name is required ")
    @Size(min = 2, message = "Customer name should have at least 2 characters")
    private String customerName;
    private String customerInitialName;
    @NotEmpty
    @Pattern(
            regexp = "^COM[0-9]{4}$",
            message = "Company id is not valid pleas flow the this format ex:- COM####")
    private String companyId;
    @NotEmpty(message = "Customer nic is required ")
    @Pattern(regexp = "^(?:19|20)?\\d{2}[0-9]{10}|[0-9]{9}[x|X|v|V]$")
    private String customerNic;
    @NotEmpty
    @Pattern(regexp = "^male$|^female$")
    private String gender;
    @NotEmpty(message = "City is required ")
    @Size(min = 2, message = "Customer city should have at least 2 characters")
    private String city;
    @NotEmpty(message = "Customer address is required ")
    @Size(min = 2)
    private String address;
    @NotEmpty
    @Pattern(regexp = "^(?:7|0|(?:\\+94))[0-9]{9,10}$",
            message = "Customer mobile number is not valid")
    private String mobileNumber;
    @Pattern(regexp = "^(?:7|0|(?:\\+94))[0-9]{9,10}$",
            message = "Customer land phone number 2 is not valid")
    private String landPhoneNumber;
    @Email(message = "Customer email is not valid")
    private String email;
    private String nicPicFront;
    private String nicPicBack;
    @NotEmpty
    @Pattern(regexp = "^ACTIVATED$|^INACTIVATED$|^DELETED$")
    private String status;
}
