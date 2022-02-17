/*
 *
 *  *  * ---------------------------------------------------------------------------------------------
 *  *  *  *  Copyright (c) IJSE-intern. All rights reserved.
 *  *  *  *  Licensed under the MIT License. See License.txt in the project root for license information.
 *  *  *  --------------------------------------------------------------------------------------------/
 *
 */

package com.example.customercrud.util.mapper;

import com.example.customercrud.dto.CustomerDTO;
import com.example.customercrud.entity.Customer;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 5/27/2021
 **/

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CustomerDTO customerDTO);
    CustomerDTO toCustomerDTO(Customer customer);
    List<CustomerDTO> pageToCustomerDTO(Page<Customer> customers);
    List<Customer> toCustomerAll(List<CustomerDTO> customerDTOS);
}
