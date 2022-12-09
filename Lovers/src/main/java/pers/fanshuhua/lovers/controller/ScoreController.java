package pers.fanshuhua.lovers.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.fanshuhua.lovers.Interceptor.LoginHandlerInterceptor;
import pers.fanshuhua.lovers.enitiy.Score;
import pers.fanshuhua.lovers.enitiy.User;
import pers.fanshuhua.lovers.service.ScoreService;

/**
 * @author 小王的饭饭
 * @create 2022/12/1 15:05
 */
@RestController
@RequestMapping("/Score")
@Log4j2
public class ScoreController {


    private final ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }


    @PostMapping("/getOneselfScore")
    public Score getOneselfScore() {
        User user = LoginHandlerInterceptor.RESOURCE.get();
        return scoreService.getOneselfScore(user);
    }

    @PostMapping("/getLoversScore")
    public Score getLoversScore(String session) {
        User user = LoginHandlerInterceptor.RESOURCE.get();
        return scoreService.getLoversScore(user);
    }
}
