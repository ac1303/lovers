package pers.fanshuhua.lovers.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.fanshuhua.lovers.enitiy.ScoreHistory;
import pers.fanshuhua.lovers.service.ScoreHistoryService;
import pers.fanshuhua.lovers.service.SigninService;
import pers.fanshuhua.lovers.service.impl.SigninServiceImpl;

/**
 * @author 小王的饭饭
 * @create 2022/12/8 12:57
 */
@RestController
@RequestMapping("/Signin")
@Log4j2
public class SigninController {
    private final SigninService signinService;
    @Autowired
    public SigninController(SigninService signinService) {
        this.signinService = signinService;
    }

    @PostMapping
    public boolean signin() {
        return signinService.signin();
    }
}
