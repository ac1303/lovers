package pers.fanshuhua.lovers.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.fanshuhua.lovers.enitiy.LoginVo;
import pers.fanshuhua.lovers.service.LoginService;

/**
 * @author 小王的饭饭
 * @create 2022/11/26 16:24
 */
@RestController
@RequestMapping("/Login")
@Log4j2
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public String login(@RequestBody JSONObject code) {
        return "login";
    }


    @PostMapping
    public LoginVo loginPost(@RequestBody JSONObject code) {
        log.info("code: "+code);
        return loginService.login(code.getString("code"));
    }
}
