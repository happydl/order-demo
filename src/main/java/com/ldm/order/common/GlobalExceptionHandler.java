package com.ldm.order.common;


import com.ldm.order.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(value = {Exception.class})
    public String defaultExceptionHandler(HttpServletRequest request,
                                          Exception exception) throws Exception{
        logger.warn(exception.getMessage(),exception);
        return "error";
    }

    @ExceptionHandler(value = { BindException.class } )
    @ResponseBody
    public ResultJSONObject validateException(HttpServletRequest request, BindException exception) throws Exception{
        logger.warn(exception.getMessage(),exception);
        return ResultJSONObject.validFailure(extractBindInfo(request,exception));
    }

    @ExceptionHandler(value = { MyException.class } )
    @ResponseBody
    public ResultJSONObject validateException(HttpServletRequest request, MyException exception) throws Exception{
        logger.warn(exception.getMessage(),exception);
        return new ResultJSONObject(exception.getErrorCode(), exception.getMessage());
    }


    private Map extractBindInfo(HttpServletRequest request, BindException bindException){
        Map<String,Object> message = new HashMap<String, Object>();
        bindException.getFieldErrors().forEach( e -> message.put(e.getField(),e.getDefaultMessage()));
        return message;
    }

}
