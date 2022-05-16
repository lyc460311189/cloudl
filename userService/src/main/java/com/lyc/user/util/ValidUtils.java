package com.lyc.user.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @author liyuchun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/16 14:43
 */
public class ValidUtils {

    public static String getErrorMessage(BindingResult bindingResult){
        StringBuffer sb=new StringBuffer();
        for(FieldError fieldError:bindingResult.getFieldErrors())
        {
            sb.append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage()).append("<br>");
        }
        return sb.toString();
    }
}
