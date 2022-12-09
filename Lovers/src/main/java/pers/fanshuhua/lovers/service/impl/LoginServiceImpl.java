package pers.fanshuhua.lovers.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.fanshuhua.lovers.dao.LoversDao;
import pers.fanshuhua.lovers.dao.ScoreDao;
import pers.fanshuhua.lovers.dao.UserDao;
import pers.fanshuhua.lovers.enitiy.LoginVo;
import pers.fanshuhua.lovers.enitiy.User;
import pers.fanshuhua.lovers.service.LoginService;
import pers.fanshuhua.lovers.uitl.HttpRequest;

/**
 * @author 小王的饭饭
 * @create 2022/11/26 16:31
 */
@Service
@Log4j2
public class LoginServiceImpl implements LoginService {

    private final HttpRequest httpRequest;
    private final UserDao userDao;
    private final ScoreDao scoreDao;
    private final LoversDao loversDao;

    @Autowired
    public LoginServiceImpl(HttpRequest httpRequest, UserDao userDao, ScoreDao scoreDao, LoversDao loversDao) {
        this.httpRequest = httpRequest;
        this.userDao = userDao;
        this.scoreDao = scoreDao;
        this.loversDao = loversDao;
    }

    @Override
    public LoginVo login(String code) {
//        根据code获取openid
        User user = httpRequest.getOpenIdAndSession(code);
        log.info("从腾讯获取的信息为："+user);

//        判断在数据库中是否存在
        User userMysql = userDao.getUser(user.getOpenId());

        if (userMysql == null) {
            log.info("用户不存在，拒绝登录"+user);
            throw new RuntimeException("陌生人不可以窥探别人的秘密哦");
        } else {
            log.info("用户存在，开始更新session_key");
            user.setCompetence(userMysql.getCompetence());
            userDao.updateUser(user);
            log.info("更新成功"+user);
        }

        log.info("开始填充返回信息");
        LoginVo loginVo = new LoginVo();
//        先填充其他信息，出现 null 的原因可能是因为数据被脱敏了
        loginVo.setLovers(loversDao.getLovers(user.getOpenId()).setOpenId(null).setLoversOpenId(null));
        loginVo.setUser(user.setOpenId(null));

        log.info("返回信息填充完成"+ loginVo);
        return loginVo;
    }
}
