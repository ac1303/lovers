package pers.fanshuhua.lovers.Interceptor;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import pers.fanshuhua.lovers.dao.UserDao;
import pers.fanshuhua.lovers.enitiy.User;
import pers.fanshuhua.lovers.exception.AuthorizeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 小王的饭饭
 * @create 2022/11/25 19:26
 */
@Component
@Log4j2
public class LoginHandlerInterceptor implements HandlerInterceptor {

    private UserDao userDao;
    public final static ThreadLocal<User> RESOURCE = new ThreadLocal<>();

    @Autowired
    public LoginHandlerInterceptor(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        获取header中的session
        String session = request.getHeader("session");
//        判断session是否存在
        User user = userDao.getUserBySessionkey(session);
        if (user == null) {
            log.info("用户不存在或已过期");
            throw new AuthorizeException("用户不存在或已过期");
        }
        RESOURCE.set(user);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RESOURCE.remove();
    }
}
