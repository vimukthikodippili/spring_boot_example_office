/*
 *
 *  *  *  * ---------------------------------------------------------------------------------------------
 *  *  *  *  *  Copyright (c) IJSE-intern. All rights reserved.
 *  *  *  *  *  Licensed under the MIT License. See License.txt in the project root for license information.
 *  *  *  *  --------------------------------------------------------------------------------------------/
 *
 */

package com.example.customercrud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 7/27/2021
 **/

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntryDuplicateException extends RuntimeException{
    public EntryDuplicateException(String message) {
        super(message);
    }

    public EntryDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}
