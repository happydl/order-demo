package com.ldm.order.exception;

import com.ldm.order.common.RespCode;

public class MyException extends RuntimeException {

    private RespCode errorCode;

    public RespCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(RespCode errorCode) {
        this.errorCode = errorCode;
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(RespCode respCode, String message) {
        super(message);
        setErrorCode(respCode);
    }

}
