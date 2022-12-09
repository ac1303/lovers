package pers.fanshuhua.lovers.service;

import pers.fanshuhua.lovers.enitiy.ScoreHistory;

import java.util.List;

/**
 * @author 小王的饭饭
 * @create 2022/12/1 16:00
 */
public interface ScoreHistoryService {


    /**
     * 插入历史得分
     *
     * @param scoreHistory 分数历史
     * @return boolean
     */
    boolean insertScoreHistory(ScoreHistory scoreHistory);

    /**
     * 得到分数历史列表
     *
     * @param start 开始
     * @param end   结束
     * @return {@link List}<{@link ScoreHistory}>
     */
    List<ScoreHistory> getScoreHistoryList(int start, int end);
}
