package pers.fanshuhua.lovers.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;
import pers.fanshuhua.lovers.enitiy.resultcode.ResultCode;
import pers.fanshuhua.lovers.enitiy.resultcode.ResultVo;
import pers.fanshuhua.lovers.exception.AuthorizeException;

import java.io.IOException;

/**
 * 捕获异常，并返回给前端
 *
 * @author 范书华
 * @create 2022/8/12 21:55
 */
@RestControllerAdvice
public class ExceptionAdvice {

    private static final Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);

    /**
     * 异常默认返回数据
     *
     * @param e e
     * @return {@link ResultVo}
     */
    @ExceptionHandler(Exception.class)
    public ResultVo doException(Exception e) {
        log.error("未知异常", e);
        return new ResultVo(ResultCode.FAIL, "网络拥挤，请稍后再试");
    }

    /**
     * 文件大小超过限制
     *
     * @param e e
     * @return {@link ResultVo}
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResultVo doMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error("文件大小超过限制", e);
        return new ResultVo(ResultCode.FILE_UPLOAD_ERROR, "文件大小超出限制");
    }

    /**
     * IO异常
     *
     * @param e e
     * @return {@link ResultVo}
     */
    @ExceptionHandler(IOException.class)
    public ResultVo doIoException(IOException e) {
        log.error("IO异常", e);
        return new ResultVo(ResultCode.FILE_ERROR, "文件读写异常");
    }


    /**
     * 授权异常
     *
     * @param e e
     * @return {@link ResultVo}
     */
    @ExceptionHandler(AuthorizeException.class)
    public ResultVo doAuthorizeException(AuthorizeException e) {
        log.error("授权异常", e);
        return new ResultVo(e.getCode(), e.getMsg(), e.getMessage());
    }

    /**
     * 算术异常
     *
     * @param e e
     * @return {@link ResultVo}
     */
    @ExceptionHandler(ArithmeticException.class)
    public ResultVo doArithmeticException(ArithmeticException e) {
        log.error("算术异常", e);
        return new ResultVo(ResultCode.FAIL, "异常的运算条件", e.getMessage());
    }

    /**
     * 主键重复异常
     *
     * @param e e
     * @return {@link ResultVo}
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ResultVo doDuplicateKeyException(DuplicateKeyException e) {
        log.error("主键重复异常", e);
        return new ResultVo(ResultCode.SQL_ERROR, e.getCause().getLocalizedMessage());
    }

    /**
     * 参数校验异常处理
     *
     * @param e e
     * @return {@link ResultVo}
     */
    @ExceptionHandler({BindException.class})
    public ResultVo methodArgumentNotValidExceptionHandler(BindException e) {
        // 从异常对象中拿到ObjectError对象
        log.error("参数校验异常", e);
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new ResultVo(ResultCode.VALIDATE_ERROR, objectError.getDefaultMessage());
    }



    /**
     * 运行时异常
     *
     * @param e e
     * @return {@link ResultVo}
     */
    @ExceptionHandler(RuntimeException.class)
    public ResultVo doRuntimeException(RuntimeException e) {
        log.error("运行时异常", e);
        return new ResultVo(ResultCode.FAIL, e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultVo doHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("请求方式不支持", e);
        return new ResultVo(ResultCode.FAIL, "不支持的请求方法");
    }
}
