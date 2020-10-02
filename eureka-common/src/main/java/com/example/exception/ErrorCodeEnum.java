package com.example.exception;

import lombok.Getter;

/**
 * 业务错误码：返回结果 状态码
 */
@Getter
public enum ErrorCodeEnum implements BaseCodeInterface{
    BODY_NOT_MATCH(400,"请求的数据格式不符！"),
    SINGNATURE_NOT_MATCH(401,"请求的数字签名不匹配！"),
    NOT_FOUND(404,"未找到该资源！"),
    INTERNAL_SERVER_ERROR(500,"服务器内部错误！"),
    USER_ACCOUNT_NOT_FOUND(10001,"账号不存在！"),
    DONOTALLOWTODISABLETHECURRENTUSER(10002,"不允许禁用当前用户！"),
    SERVER_BUSY(503,"服务器正忙！请稍后再试！");

    private int resultCode;
    private String resultMsg;

    ErrorCodeEnum(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public int getResultCode() {
        return 0;
    }

    @Override
    public int getResultMsg() {
        return 0;
    }
}
