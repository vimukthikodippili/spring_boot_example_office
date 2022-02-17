package com.example.customercrud.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Akila Sasanka <akilasasanka1998@gmail.com>
 * @since : 5/23/2021
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardResponse {
    private int code;
    private String message;
    private Object data;
}
