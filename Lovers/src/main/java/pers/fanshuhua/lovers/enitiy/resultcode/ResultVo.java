package pers.fanshuhua.lovers.enitiy.resultcode;

import lombok.Data;

/**
 * @author 范书华
 * @create 2022/8/12 21:41
 */
@Data
public class ResultVo {
    private int code;
    private String msg;
    private Object data;

    /**
     * 手动返回状态码
     *
     * @param code 状态码
     * @param msg  状态信息
     * @param data 数据
     */
    public ResultVo(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 默认状态码SUCCESS
     *
     * @param data 数据
     */
    public ResultVo(Object data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.data = data;
    }

    /**
     * 自定义状态码的信息
     *
     * @param statusCode 状态码
     * @param msg        状态信息
     * @param data       返回的数据
     */
    public ResultVo(ResultCode statusCode, String msg, Object data) {
        this.code = statusCode.getCode();
        this.msg = msg;
        this.data = data;
    }

    /**
     * 指定状态码
     *
     * @param statusCode 状态码
     * @param data       数据
     */
    public ResultVo(ResultCode statusCode, Object data) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = data;
    }

    /**
     * 只返回状态码
     *
     * @param statusCode 状态码
     */
    public ResultVo(ResultCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = null;
    }
}
