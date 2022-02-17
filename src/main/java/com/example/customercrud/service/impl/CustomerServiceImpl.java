/*
 *
 *  *  * ---------------------------------------------------------------------------------------------
 *  *  *  *  Copyright (c) IJSE-intern. All rights reserved.
 *  *  *  *  Licensed under the MIT License. See License.txt in the project root for license information.
 *  *  *  --------------------------------------------------------------------------------------------/
 *
 */

package com.example.customercrud.service.impl;


import com.example.customercrud.dto.CustomerDTO;
import com.example.customercrud.dto.paginated.PaginatedCustomerDTO;
import com.example.customercrud.entity.Customer;
import com.example.customercrud.exception.EntryDuplicateException;
import com.example.customercrud.exception.EntryNotFoundException;
import com.example.customercrud.repository.CustomerRepo;
import com.example.customercrud.service.CustomerService;
import com.example.customercrud.util.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 5/27/2021
 **/

@Service

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        customerDTO.setCustomerId(getLastCustomerId(customerDTO.getCompanyId()));
        if (!customerRepo.existsByCompanyIdEqualsAndCustomerNicEquals(customerDTO.getCompanyId(), customerDTO.getCustomerNic())){
            if (!customerRepo.existsById(customerDTO.getCustomerId())){
                return customerRepo.save(customerMapper.toCustomer(customerDTO)).getCustomerId().substring(4);
            }else {
                throw new EntryDuplicateException("Customer id is already exists");
            }
        } else {
            throw  new EntryDuplicateException("Customer is already exists");
        }
    }

    @Override
    public String updateCustomer(CustomerDTO customerDTO) {
        if (customerRepo.existsById(customerDTO.getCustomerId())){
            return customerRepo.save(customerMapper.toCustomer(customerDTO)).getCustomerId().substring(4);
        }else {
            throw new EntryNotFoundException("Customer is not exist");
        }
    }

    @Override
    public String removeCustomer(String id) {
        Customer customer = customerRepo.findById(id).orElseThrow(() ->
                new EntryNotFoundException("Customer is not exists")
        );
        customer.setStatus("DELETED");
        return customerRepo.save(customer).getCustomerId().substring(4);
    }

    @Transactional
    @Override
    public String inactivateCustomer(String id) {
        Customer customer = customerRepo.findById(id).orElseThrow(() ->
                new EntryNotFoundException("Customer is not exists")
        );
            customer.setStatus("INACTIVATED");
            return customerRepo.save(customer).getCustomerId().substring(4);
    }

    @Override
    public String activateCustomer(String id) {
        Customer customer = customerRepo.findById(id).orElseThrow(() ->
                new EntryNotFoundException("Customer is not exists")
        );
        customer.setStatus("ACTIVATED");
        return customerRepo.save(customer).getCustomerId().substring(4);
    }

    @Override
    public PaginatedCustomerDTO searchAllCustomer(int page, int pageSize, String companyId, String name, String status) {
        return new PaginatedCustomerDTO(
                customerMapper.pageToCustomerDTO(customerRepo.
                        findByStatusEqualsAndCompanyIdIsAndCustomerNameContainingOrStatusEqualsAndCompanyIdIsAndCustomerIdContainingOrStatusEqualsAndCompanyIdIsAndCustomerNicContainingOrStatusEqualsAndCompanyIdIsAndAddressContainingOrStatusEqualsAndCompanyIdIsAndLandPhoneNumberContainingOrStatusEqualsAndCompanyIdIsAndMobileNumberContaining(
                                status, companyId, name, status, companyId, name,status, companyId, name,status, companyId, name,status, companyId, name,status, companyId, name, PageRequest.of(page,pageSize)
                        )),
                customerRepo.countByStatusEqualsAndCompanyIdIsAndCustomerNameContainingOrStatusEqualsAndCompanyIdIsAndCustomerIdContainingOrStatusEqualsAndCompanyIdIsAndCustomerNicContainingOrStatusEqualsAndCompanyIdIsAndAddressContainingOrStatusEqualsAndCompanyIdIsAndLandPhoneNumberContainingOrStatusEqualsAndCompanyIdIsAndMobileNumberContaining(
                        status, companyId, name,status, companyId, name,status, companyId, name,status, companyId, name,status, companyId, name,status, companyId, name
                ));
    }

    @Override
    public PaginatedCustomerDTO searchBYCustomerId(int page, int pageSize, String companyId, String id, String status) {
        return new PaginatedCustomerDTO(
                customerMapper.pageToCustomerDTO(
                        customerRepo.findByCustomerIdContainingAndCompanyIdIsAndStatusEquals(
                                id, companyId, status, PageRequest.of(page,pageSize))),
                customerRepo.countByCustomerIdContainingAndCompanyIdIsAndStatusEquals(id, companyId, status));
    }

    @Override
    public PaginatedCustomerDTO searchByCustomerName(int page, int pageSize, String companyId, String name, String status) {
        return new PaginatedCustomerDTO(
                customerMapper.pageToCustomerDTO(
                        customerRepo.findByCustomerNameContainingAndCompanyIdIsAndStatusEquals(
                name, companyId, status, PageRequest.of(page,pageSize))),
                customerRepo.countByCustomerNameContainingAndCompanyIdIsAndStatusEquals(name, companyId, status));
    }

    @Override
    public PaginatedCustomerDTO searchByCustomerNic(int page, int pageSize, String companyId, String nic, String status) {
        return new PaginatedCustomerDTO(
                customerMapper.pageToCustomerDTO(
                        customerRepo.findByCustomerNicContainingAndCompanyIdIsAndStatusEquals(
                nic, companyId, status, PageRequest.of(page,pageSize))),
                customerRepo.countByCustomerNicContainingAndCompanyIdIsAndStatusEquals(nic, companyId, status));
    }

    @Override
    public PaginatedCustomerDTO searchByCustomerAddress(int page, int pageSize, String companyId, String address, String status) {
        return new PaginatedCustomerDTO(
                customerMapper.pageToCustomerDTO(
                        customerRepo.findByAddressContainingAndCompanyIdIsAndStatusEquals(
                address, companyId, status, PageRequest.of(page,pageSize))),
                customerRepo.countByAddressContainingAndCompanyIdIsAndStatusEquals(address, companyId, status));
    }

    @Override
    public PaginatedCustomerDTO searchBYCustomerMobile(int page, int pageSize, String companyId, String mobile, String status) {
        return new PaginatedCustomerDTO(
                customerMapper.pageToCustomerDTO(
                        customerRepo.findByMobileNumberContainingAndCompanyIdIsAndStatusEquals(
                mobile, companyId, status, PageRequest.of(page,pageSize))),
                customerRepo.countByMobileNumberContainingAndCompanyIdIsAndStatusEquals(mobile, companyId, status));
    }

    @Override
    public PaginatedCustomerDTO searchByCustomerLand(int page, int pageSize, String companyId, String land, String status) {
        return new PaginatedCustomerDTO(
                customerMapper.pageToCustomerDTO(
                        customerRepo.findByLandPhoneNumberContainingAndCompanyIdIsAndStatusEquals(
                land, companyId, status, PageRequest.of(page,pageSize))),
                customerRepo.countByLandPhoneNumberContainingAndCompanyIdIsAndStatusEquals(land, companyId, status));
    }

    @Override
    public PaginatedCustomerDTO getAllActivatedCustomer(String companyId, int page, int pageSize) {
        return new PaginatedCustomerDTO(
                customerMapper.pageToCustomerDTO(
                        customerRepo.findAllByStatusEqualsAndCompanyIdEquals(
                "ACTIVATED", companyId, PageRequest.of(page,pageSize))),

                customerRepo.countByStatusEqualsAndCompanyIdEquals("ACTIVATED", companyId));
    }

    @Override
    public PaginatedCustomerDTO getAllInactivatedCustomer(String companyId, int page, int pageSize) {
        return new PaginatedCustomerDTO(
                customerMapper.pageToCustomerDTO(
                        customerRepo.findAllByStatusEqualsAndCompanyIdEquals(
                "INACTIVATED", companyId, PageRequest.of(page,pageSize))),
                customerRepo.countByStatusEqualsAndCompanyIdEquals("INACTIVATED", companyId));
    }

    @Override
    public String getLastCustomerId(String companyId) {
//        Company company = companyRepo.findById(companyId).orElseThrow(() ->
//                new EntryNotFoundException("Company is not exist")
//        );
//        List<Customer> ids = customerRepo.findAllByCompanyIdEquals(companyId);
//        if (ids.isEmpty()){
//            return company.getCompanyCode()+"-CUS0001";
//        } else {
//            return String.format(company.getCompanyCode()+ "-CUS%04d",
//                    Integer.parseInt(ids.get(ids.size()-1).getCustomerId().substring(7))+1);
//        }
        return  null;
    }
}
