package com.example.exception;

/**
 *功能描述：自定义的错误描述枚举类需要实现该接口
 */
public interface BaseCodeInterface {
    /**
     * 错误码
     * @return
     */
    int getResultCode();

    /**
     * 错误描述
     * @return
     */
    int getResultMsg();
}
