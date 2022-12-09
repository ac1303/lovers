package pers.fanshuhua.lovers.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.fanshuhua.lovers.enitiy.ScoreHistory;
import pers.fanshuhua.lovers.service.ScoreHistoryService;

import java.util.List;

/**
 * @author 小王的饭饭
 * @create 2022/12/1 17:14
 */
@RestController
@RequestMapping("/ScoreHistory")
@Log4j2
public class ScoreHistoryController {
    private final ScoreHistoryService scoreHistoryService;
    @Autowired
    public ScoreHistoryController(ScoreHistoryService scoreHistoryService) {
        this.scoreHistoryService = scoreHistoryService;
    }

    @PostMapping
    public boolean install(ScoreHistory scoreHistory) {
        return scoreHistoryService.insertScoreHistory(scoreHistory);
    }

    @GetMapping
    public List<ScoreHistory> getScoreHistoryList(int start, int end) {
        return scoreHistoryService.getScoreHistoryList(start, end);
    }
}
