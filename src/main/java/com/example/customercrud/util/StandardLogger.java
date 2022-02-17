package com.example.customercrud.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 5/23/2021
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StandardLogger {
    private String type;
    private String message;
}
