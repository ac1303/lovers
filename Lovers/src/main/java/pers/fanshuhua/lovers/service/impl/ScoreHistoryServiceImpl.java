package pers.fanshuhua.lovers.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.fanshuhua.lovers.Interceptor.LoginHandlerInterceptor;
import pers.fanshuhua.lovers.dao.LoversDao;
import pers.fanshuhua.lovers.dao.ScoreHistoryDao;
import pers.fanshuhua.lovers.enitiy.Lovers;
import pers.fanshuhua.lovers.enitiy.ScoreHistory;
import pers.fanshuhua.lovers.enitiy.User;
import pers.fanshuhua.lovers.service.ScoreHistoryService;

import java.util.List;

/**
 * @author 小王的饭饭
 * @create 2022/12/1 16:02
 */
@Service
@Log4j2
public class ScoreHistoryServiceImpl implements ScoreHistoryService {

    private final ScoreHistoryDao ScoreHistoryDao;
    private final LoversDao loversDao;

    @Autowired
    public ScoreHistoryServiceImpl(ScoreHistoryDao scoreHistoryDao, LoversDao loversDao) {
        ScoreHistoryDao = scoreHistoryDao;
        this.loversDao = loversDao;
    }

    @Override
    public boolean insertScoreHistory(ScoreHistory scoreHistory) {
        User user = LoginHandlerInterceptor.RESOURCE.get();
        Lovers lovers = loversDao.getLovers(user.getOpenId());
        if (lovers.getOpenId().equals(user.getOpenId())){
            scoreHistory.setOpenId(lovers.getLoversOpenId());
            scoreHistory.setLoversOpenId(lovers.getOpenId());
        }else {
            scoreHistory.setOpenId(lovers.getOpenId());
            scoreHistory.setLoversOpenId(lovers.getLoversOpenId());
        }
        int i = ScoreHistoryDao.insertScoreHistory(scoreHistory);
        return i >= 1;
    }

    @Override
    public List<ScoreHistory> getScoreHistoryList(int start, int end) {
        List<ScoreHistory> scoreHistorys = ScoreHistoryDao.getScoreHistory(start, end);
        log.info("scoreHistory:{}", scoreHistorys);
        User user = LoginHandlerInterceptor.RESOURCE.get();
        for (ScoreHistory scoreHistory : scoreHistorys) {
            if (user.getOpenId().equals(scoreHistory.getOpenId())) {
                scoreHistory.setOpenId("我");
            } else {
                scoreHistory.setOpenId("Ta");
            }
        }
        return scoreHistorys;
    }
}
