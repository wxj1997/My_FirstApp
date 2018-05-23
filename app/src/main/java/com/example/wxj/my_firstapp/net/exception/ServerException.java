package com.example.wxj.my_firstapp.net.exception;

/**
 * Created by wxj on 2018/5/23.
 */

public class ServerException extends Exception {
    private int code;
    private String message;
    public ServerException(int code,String message){
        this.code=code;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
