package pers.fanshuhua.lovers.controller.advice;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import pers.fanshuhua.lovers.enitiy.resultcode.ResultVo;


/**
 * 对返回结果进行统一处理
 *
 * @author 范书华
 * @create 2022/8/12 21:43
 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    private static final Logger log = LoggerFactory.getLogger(ResponseAdvice.class);

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // response是ResultVo类型，或者注释了NotControllerResponseAdvice都不进行包装
        return !(returnType.getParameterType().isAssignableFrom(ResultVo.class)
                || returnType.hasMethodAnnotation(NotControllerResponseAdvice.class)
                || returnType.getContainingClass().isAnnotationPresent(NotControllerResponseAdvice.class));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // String类型不能直接包装
//        if (returnType.getGenericParameterType().equals(String.class)) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            try {
//                // 将数据包装在ResultVo里后转换为json串进行返回
//                return objectMapper.writeValueAsString(new ResultVo(body));
//            } catch (JsonProcessingException e) {
//                throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
//            }
//        }

        if (body instanceof String) {
            return JSON.toJSONString(new ResultVo(body));
        }

        if (body instanceof ResultVo) {
            return body;
        }

        // 否则直接包装成ResultVo返回、
        return new ResultVo(body);
    }
}
