package pers.fanshuhua.lovers.service.impl;

import org.springframework.stereotype.Service;
import pers.fanshuhua.lovers.Interceptor.LoginHandlerInterceptor;
import pers.fanshuhua.lovers.dao.LoversDao;
import pers.fanshuhua.lovers.dao.ScoreHistoryDao;
import pers.fanshuhua.lovers.enitiy.Lovers;
import pers.fanshuhua.lovers.enitiy.ScoreHistory;
import pers.fanshuhua.lovers.enitiy.User;
import pers.fanshuhua.lovers.service.SigninService;

import java.sql.Timestamp;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

/**
 * @author 小王的饭饭
 * @create 2022/12/8 13:03
 */
@Service
public class SigninServiceImpl implements SigninService {

    private final ScoreHistoryDao ScoreHistoryDao;
    private final LoversDao loversDao;

    public SigninServiceImpl(ScoreHistoryDao scoreHistoryDao,LoversDao loversDao) {
        this.ScoreHistoryDao = scoreHistoryDao;
        this.loversDao = loversDao;
    }
    @Override
    public boolean signin() {
        User user = LoginHandlerInterceptor.RESOURCE.get();
        Lovers lovers = loversDao.getLovers(user.getOpenId());
        ScoreHistory scoreHistory = new ScoreHistory();
        scoreHistory.setUuid(UUID.randomUUID().toString());
        scoreHistory.setOpenId(user.getOpenId());
        if (lovers.getOpenId().equals(user.getOpenId())) {
            scoreHistory.setLoversOpenId(lovers.getLoversOpenId());
        }else {
            scoreHistory.setLoversOpenId(lovers.getOpenId());
        }
        scoreHistory.setOperation(1);
        scoreHistory.setScore(5);
        scoreHistory.setReason("签到");
        scoreHistory.setTime(new Timestamp(System.currentTimeMillis()));

// 获取今天年月日0点0分0秒的时间戳
        long current = System.currentTimeMillis();
        long zero = current/(1000*3600*24)*(1000*3600*24) - TimeZone.getDefault().getRawOffset();
        List<ScoreHistory> scoreHistories = ScoreHistoryDao.checkTodaySign(
                scoreHistory.getOpenId(),
                scoreHistory.getLoversOpenId(),
                scoreHistory.getReason(),
//                填入今天0点0分0秒的时间戳
                new Timestamp(zero)
        );
        if (scoreHistories.size() > 0) {
            throw new RuntimeException("今天已经签到过了");
        }

        int i = ScoreHistoryDao.insertScoreHistory(scoreHistory);
        if (i >= 1) {
            return true;
        }
        throw new RuntimeException("签到失败");
    }
}
