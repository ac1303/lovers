package pers.fanshuhua.lovers.controller.advice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 取消封装响应结果
 *
 * @author 范书华
 * @create 2022/8/12 21:45
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotControllerResponseAdvice {
}
