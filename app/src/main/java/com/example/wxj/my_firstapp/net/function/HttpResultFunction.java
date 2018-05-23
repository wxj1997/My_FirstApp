package com.example.wxj.my_firstapp.net.function;


import com.example.wxj.my_firstapp.net.HttpResult;
import com.example.wxj.my_firstapp.net.exception.ServerException;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class HttpResultFunction<T> implements Function<HttpResult<T>, T> {
    @Override
    public T apply(@NonNull HttpResult<T> tHttpResult) throws Exception {
        if (tHttpResult.getCode() != 0) {
            throw new ServerException(tHttpResult.getCode(),tHttpResult.getMessage());
        } else {
            return tHttpResult.getData();
        }
    }
}
