/*
 *
 *  *  *  * ---------------------------------------------------------------------------------------------
 *  *  *  *  *  Copyright (c) IJSE-intern. All rights reserved.
 *  *  *  *  *  Licensed under the MIT License. See License.txt in the project root for license information.
 *  *  *  *  --------------------------------------------------------------------------------------------/
 *
 */

package com.example.customercrud.dto.paginated;

import com.example.customercrud.dto.CustomerDTO;
import com.example.customercrud.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 5/27/2021
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginatedCustomerDTO implements SuperDTO {
    private List<CustomerDTO> list;
    private long dataCount;
}
