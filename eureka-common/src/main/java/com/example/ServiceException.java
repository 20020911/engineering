package com.example;

import com.example.exception.BaseCodeInterface;
import lombok.Data;

@Data
public class ServiceException extends RuntimeException{
    private static final long serialVersionUID=1L;

    protected int errorCode;
    protected String errorMsg;
    public ServiceException(){
        super();
    }
    public ServiceException(String errorMsg){
        super(errorMsg);
        this.errorMsg= errorMsg;
    }
    public ServiceException(BaseCodeInterface errorInfoInterface){
        this.errorCode = errorInfoInterface.getResultCode();
        //this.errorMsg = Integer.parseInt(errorInfoInterface.getResultMsg();
    }
    public ServiceException(BaseCodeInterface errorInfoInterface,String errorMsg){
        this.errorCode = errorInfoInterface.getResultCode();
        this.errorMsg = errorMsg;
    }
}
