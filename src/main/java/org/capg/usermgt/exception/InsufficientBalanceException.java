package org.capg.usermgt.exception;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String msg){
        super(msg);
    }
}
