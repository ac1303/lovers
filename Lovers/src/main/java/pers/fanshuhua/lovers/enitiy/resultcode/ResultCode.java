package pers.fanshuhua.lovers.enitiy.resultcode;

import lombok.Getter;

/**
 * @author 范书华
 * @create 2022/8/12 21:37
 */
@Getter
public enum ResultCode {
    /*
     * 自定义状态码
     **/
    SUCCESS(200, "请求成功"),
    FAIL(400, "请求失败"),
    UNAUTHORIZED(401, "授权异常"),
    NOT_FOUND(404, "没有找到资源"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    VALIDATE_ERROR(1001, "参数校验失败"),
    SQL_ERROR(2001, "数据库操作异常"),
    REDIS_ERROR(2002, "Redis操作异常"),
    FILE_ERROR(3001, "文件上传失败"),
    FILE_UPLOAD_ERROR(3002, "文件上传失败"),
    ;

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
