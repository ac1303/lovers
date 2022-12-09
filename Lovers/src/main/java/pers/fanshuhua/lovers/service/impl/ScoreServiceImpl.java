package pers.fanshuhua.lovers.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.fanshuhua.lovers.dao.LoversDao;
import pers.fanshuhua.lovers.dao.ScoreDao;
import pers.fanshuhua.lovers.dao.UserDao;
import pers.fanshuhua.lovers.enitiy.Lovers;
import pers.fanshuhua.lovers.enitiy.Score;
import pers.fanshuhua.lovers.enitiy.User;
import pers.fanshuhua.lovers.service.ScoreService;

/**
 * @author 小王的饭饭
 * @create 2022/12/1 15:07
 */
@Service
@Log4j2
public class ScoreServiceImpl implements ScoreService {

    private final UserDao userDao;
    private final LoversDao loversDao;
    private final ScoreDao scoreDao;

    @Autowired
    public ScoreServiceImpl(UserDao userDao, LoversDao loversDao, ScoreDao scoreDao) {
        this.userDao = userDao;
        this.loversDao = loversDao;
        this.scoreDao = scoreDao;
    }


    @Override
    public Score getOneselfScore(String session) {
//        根据session获取openid
        User user = userDao.getUserBySessionkey(session);
        return getOneselfScore(user);
    }

    @Override
    public Score getLoversScore(String session) {
//        根据session获取openid
        User user = userDao.getUserBySessionkey(session);
        return getLoversScore(user);
    }

    @Override
    public Score getOneselfScore(User user) {
        Score score = scoreDao.getScore(user.getOpenId());
//        score.setOpenid(null);
//        score.setLoveOpenId(null);
        log.info("获取到的分数为："+score);
        return score;
    }

    @Override
    public Score getLoversScore(User user) {
        Lovers lovers = loversDao.getLovers(user.getOpenId());
        String loversOpenid;
        if (lovers.getLoversOpenId().equals(user.getOpenId())){
            loversOpenid = lovers.getOpenId();
        }else {
            loversOpenid = lovers.getLoversOpenId();
        }

        Score score = scoreDao.getScore(loversOpenid);
//        score.setOpenid(null);
//        score.setLoveOpenId(null);
        log.info("获取到的分数为："+score);
        return score;
    }


}
