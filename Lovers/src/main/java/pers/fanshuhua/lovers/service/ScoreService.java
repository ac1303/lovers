package pers.fanshuhua.lovers.service;

import pers.fanshuhua.lovers.enitiy.Score;
import pers.fanshuhua.lovers.enitiy.User;

/**
 * @author 小王的饭饭
 * @create 2022/12/1 15:06
 */
public interface ScoreService {
    Score getOneselfScore(String session);

    Score getLoversScore(String session);

    Score getOneselfScore(User user);

    Score getLoversScore(User user);
}
