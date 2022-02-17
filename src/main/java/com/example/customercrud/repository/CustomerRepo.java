/*
 *
 *  *  * ---------------------------------------------------------------------------------------------
 *  *  *  *  Copyright (c) IJSE-intern. All rights reserved.
 *  *  *  *  Licensed under the MIT License. See License.txt in the project root for license information.
 *  *  *  --------------------------------------------------------------------------------------------/
 *
 */

package com.example.customercrud.repository;

import com.example.customercrud.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 5/27/2021
 **/

@EnableMongoRepositories
public interface CustomerRepo extends MongoRepository<Customer, String> {
    //getAllActivateOrDeactivateCustomer
    Page<Customer>
    findAllByStatusEqualsAndCompanyIdEquals(String status, String companyId, Pageable pageable);
    long countByStatusEqualsAndCompanyIdEquals(String status, String companyId);

    //searchCustomerAllField query
    Page<Customer> findByStatusEqualsAndCompanyIdIsAndCustomerNameContainingOrStatusEqualsAndCompanyIdIsAndCustomerIdContainingOrStatusEqualsAndCompanyIdIsAndCustomerNicContainingOrStatusEqualsAndCompanyIdIsAndAddressContainingOrStatusEqualsAndCompanyIdIsAndLandPhoneNumberContainingOrStatusEqualsAndCompanyIdIsAndMobileNumberContaining(
            String status, String companyId, String name,String status1,String companyId1, String id, String status2, String companyId2, String nic, String status3, String companyId3, String address, String status4, String companyId4, String land, String status5, String companyId5, String mobile, Pageable pageable);
    long countByStatusEqualsAndCompanyIdIsAndCustomerNameContainingOrStatusEqualsAndCompanyIdIsAndCustomerIdContainingOrStatusEqualsAndCompanyIdIsAndCustomerNicContainingOrStatusEqualsAndCompanyIdIsAndAddressContainingOrStatusEqualsAndCompanyIdIsAndLandPhoneNumberContainingOrStatusEqualsAndCompanyIdIsAndMobileNumberContaining(
            String status, String companyId, String name,String status1,String companyId1, String id, String status2, String companyId2, String nic, String status3, String companyId3, String address, String status4, String companyId4, String land, String status5, String companyId5, String mobile);

    //searchCustomerIdField query
    Page<Customer> findByCustomerIdContainingAndCompanyIdIsAndStatusEquals(String id, String companyId, String status, Pageable pageable);
    long countByCustomerIdContainingAndCompanyIdIsAndStatusEquals(String id, String companyId, String status);

    //searchCustomerNameField query
    Page<Customer> findByCustomerNameContainingAndCompanyIdIsAndStatusEquals(String name, String companyId, String status, Pageable pageable);
    long countByCustomerNameContainingAndCompanyIdIsAndStatusEquals(String name, String companyId, String status);

    //searchCustomerNicField query
    Page<Customer> findByCustomerNicContainingAndCompanyIdIsAndStatusEquals(String nic, String companyId, String status, Pageable pageable);
    long countByCustomerNicContainingAndCompanyIdIsAndStatusEquals(String nic, String companyId, String status);

    //searchCustomerAddressField query
    Page<Customer> findByAddressContainingAndCompanyIdIsAndStatusEquals(String address, String companyId, String status, Pageable pageable);
    long countByAddressContainingAndCompanyIdIsAndStatusEquals(String address, String companyId, String status);

    //searchCustomerMobileNumberField query
    Page<Customer> findByMobileNumberContainingAndCompanyIdIsAndStatusEquals(String mobile, String companyId, String status, Pageable pageable);
    long countByMobileNumberContainingAndCompanyIdIsAndStatusEquals(String mobile, String companyId, String status);

    //searchCustomerLandPhoneNumberField query
    Page<Customer> findByLandPhoneNumberContainingAndCompanyIdIsAndStatusEquals(String land, String companyId, String status, Pageable pageable);
    long countByLandPhoneNumberContainingAndCompanyIdIsAndStatusEquals(String land, String companyId, String status);

    List<Customer> findAllByCompanyIdEquals(String companyId);

    boolean existsByCompanyIdEqualsAndCustomerNicEquals(String companyId, String nic);
}
