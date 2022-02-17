/*
 *
 *  *  *  * ---------------------------------------------------------------------------------------------
 *  *  *  *  *  Copyright (c) IJSE-intern. All rights reserved.
 *  *  *  *  *  Licensed under the MIT License. See License.txt in the project root for license information.
 *  *  *  *  --------------------------------------------------------------------------------------------/
 *
 */

package com.example.customercrud.api;

import com.example.customercrud.dto.CustomerDTO;
import com.example.customercrud.dto.paginated.PaginatedCustomerDTO;
import com.example.customercrud.service.CustomerService;
import com.example.customercrud.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 5/27/2021
 **/

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin
@Validated
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity saveCustomer(@Valid @RequestBody CustomerDTO dto){
        String id = customerService.saveCustomer(dto);
        return new ResponseEntity(
                new StandardResponse(201, id+" success added", null),
                HttpStatus.CREATED);
    }

    @GetMapping(params = {"companyId", "page", "size", "status"})
    public ResponseEntity getAllCustomer(
            @RequestParam(value = "companyId") @Pattern(
                    regexp = "^COM[0-9]{4}$",
                    message = "Company id is not valid pleas flow the this format ex:- COM####"
            ) String companyId,
            @RequestParam(value = "page") int pageNo,
            @RequestParam(value = "size") @Max(50) int pageSize,
            @RequestParam(value = "status") @Pattern(
                    regexp = "^ACTIVATED$|^INACTIVATED$",
                    message = "Customer status is not valid"
            ) String status){
        PaginatedCustomerDTO allCustomer = null;
        switch (status) {
            case "ACTIVATED" :
                allCustomer = customerService.getAllActivatedCustomer(companyId, pageNo, pageSize);
                break;
            case "INACTIVATED" :
                allCustomer = customerService.getAllInactivatedCustomer(companyId, pageNo, pageSize);
                break;
            default :
                break;
        }
        return new ResponseEntity(
                new StandardResponse(200, "success", allCustomer),
                HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateCustomer(@Valid @RequestBody CustomerDTO dto){
        String id = customerService.updateCustomer(dto);
        return new ResponseEntity(
                new StandardResponse(204, "success", id) ,
                HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/{id}" , params = {"status"})
    public ResponseEntity deleteOrActiveOrInactiveCustomer(
            @PathVariable(value = "id") @Pattern(
                    regexp = "^[A-Z]{3}[-]CUS[0-9]{4}$",
                    message = "Customer id is not valid pleas flow the this format ex:- CUS####"
            ) String id,
            @RequestParam(value = "status") @Pattern(
                    regexp = "^ACTIVATED$|^INACTIVATED$|^DELETED$",
                    message = "Customer status is not valid"
            ) String status){
        StandardResponse response = null;
        String customerId = null;
        switch (status) {
            case "DELETED" :
                customerId = customerService.removeCustomer(id);
                response = new StandardResponse(204, customerId+" success deleted", null);
                break;
            case "INACTIVATED" :
                customerId = customerService.inactivateCustomer(id);
                response = new StandardResponse(204, customerId+" success inactivated", null);
                break;
            case "ACTIVATED" :
                customerId = customerService.activateCustomer(id);
                response = new StandardResponse(204, customerId+" success activated", null);
                break;
            default:
                break;
        }
        return new ResponseEntity(response, HttpStatus.OK);

    }

    @GetMapping(params = {"companyId","page", "size", "value", "status", "type"})
    public ResponseEntity searchCustomer(
            @RequestParam(value = "companyId") @Pattern(
                    regexp = "^COM[0-9]{4}$",
                    message = "Company id is not valid pleas flow the this format ex:- COM####"
            ) String companyId,
            @RequestParam(value = "page") int pageNo,
            @RequestParam(value = "size") @Max(50) int pageSize,
            @RequestParam(value = "value") String value,
            @RequestParam(value = "status") @Pattern(
                    regexp = "^ACTIVATED$|^INACTIVATED$",
                    message = "Customer status is not valid"
            ) String status,
            @RequestParam(value = "type") @Pattern(
                    regexp = "^ALL$|^ID$|^NAME$|^NIC$|^ADDRESS$|^MOBILE$|^LAND$",
                    message = "Customer type is not valid"
            ) String type){
        PaginatedCustomerDTO paginatedCustomerDTO = null;
        switch (type){
            case "ALL" :
                 paginatedCustomerDTO =
                        customerService.searchAllCustomer(pageNo, pageSize, companyId, value, status);
                break;
            case "ID" :
                paginatedCustomerDTO =
                        customerService.searchBYCustomerId(pageNo,pageSize, companyId, value, status);
                break;
            case "NAME" :
                paginatedCustomerDTO =
                        customerService.searchByCustomerName(pageNo,pageSize, companyId, value, status);
                break;
            case "NIC" :
                paginatedCustomerDTO =
                        customerService.searchByCustomerNic(pageNo,pageSize, companyId, value, status);
                break;
            case "ADDRESS" :
                paginatedCustomerDTO =
                        customerService.searchByCustomerAddress(pageNo,pageSize, companyId, value, status);
                break;
            case "MOBILE" :
                paginatedCustomerDTO =
                        customerService.searchBYCustomerMobile(pageNo,pageSize, companyId, value, status);
                break;
            case "LAND" :
                paginatedCustomerDTO =
                        customerService.searchByCustomerLand(pageNo,pageSize, companyId, value, status);
                break;
        }
        return new ResponseEntity(
                new StandardResponse(200, "success", paginatedCustomerDTO),
                HttpStatus.OK);
    }
}
