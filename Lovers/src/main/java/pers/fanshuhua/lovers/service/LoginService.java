package pers.fanshuhua.lovers.service;

import pers.fanshuhua.lovers.enitiy.LoginVo;

/**
 * @author 小王的饭饭
 * @create 2022/11/26 16:27
 */
public interface LoginService {
    LoginVo login(String code);
}
