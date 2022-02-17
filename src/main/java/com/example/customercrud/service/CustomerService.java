/*
 *
 *  *  * ---------------------------------------------------------------------------------------------
 *  *  *  *  Copyright (c) IJSE-intern. All rights reserved.
 *  *  *  *  Licensed under the MIT License. See License.txt in the project root for license information.
 *  *  *  --------------------------------------------------------------------------------------------/
 *
 */

package com.example.customercrud.service;


import com.example.customercrud.dto.CustomerDTO;
import com.example.customercrud.dto.paginated.PaginatedCustomerDTO;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 5/27/2021
 **/
public interface CustomerService {
    public String saveCustomer(CustomerDTO customerDTO);
    public String updateCustomer(CustomerDTO customerDTO);
    public String removeCustomer(String id);
    public String inactivateCustomer(String id);
    public String activateCustomer(String id);
    public PaginatedCustomerDTO getAllActivatedCustomer(String companyId, int page, int pageSize);
    public PaginatedCustomerDTO getAllInactivatedCustomer(String companyId, int page, int pageSize);
    public String getLastCustomerId(String companyId);
    public PaginatedCustomerDTO searchAllCustomer(
            int page, int pageSize, String companyId, String value, String status);
    public PaginatedCustomerDTO searchBYCustomerId(
            int page, int pageSize, String companyId, String id, String status);
    public PaginatedCustomerDTO searchByCustomerName(
            int page, int pageSize, String companyId, String name, String status);
    public PaginatedCustomerDTO searchByCustomerNic(
            int page, int pageSize, String companyId, String nic, String status);
    public PaginatedCustomerDTO searchByCustomerAddress(
            int page, int pageSize, String companyId, String address, String status);
    public PaginatedCustomerDTO searchBYCustomerMobile(
            int page, int pageSize, String companyId, String mobile, String status);
    public PaginatedCustomerDTO searchByCustomerLand(
            int page, int pageSize, String companyId, String land, String status);
}
