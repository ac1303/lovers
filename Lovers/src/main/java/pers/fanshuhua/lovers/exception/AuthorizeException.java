package pers.fanshuhua.lovers.exception;

import lombok.Getter;
import pers.fanshuhua.lovers.enitiy.resultcode.ResultCode;

/**
 * 自定义授权异常
 *
 * @author 范书华
 * @create 2022/8/12 22:40
 */
@Getter
public class AuthorizeException extends RuntimeException {
    private int code;
    private String msg;

    public AuthorizeException(ResultCode statusCode, String msg) {
        super(msg);
        this.code = statusCode.getCode();
        this.msg = msg;
    }

    public AuthorizeException(ResultCode statusCode) {
        super(statusCode.getMsg());
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

    public AuthorizeException(String msg) {
        super(msg);
        this.code = ResultCode.UNAUTHORIZED.getCode();
        this.msg = msg;
    }

    public AuthorizeException() {
        super(ResultCode.UNAUTHORIZED.getMsg());
        this.code = ResultCode.UNAUTHORIZED.getCode();
        this.msg = ResultCode.UNAUTHORIZED.getMsg();
    }
}
