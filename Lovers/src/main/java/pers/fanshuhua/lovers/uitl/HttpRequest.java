package pers.fanshuhua.lovers.uitl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pers.fanshuhua.lovers.enitiy.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * @author 小王的饭饭
 * @create 2022/11/23 19:54
 */
@Component
@Log4j2
public class HttpRequest {


    @Value("${appid}")
    private String appid;
    @Value("${secret}")
    private String secret;
    @Value("${grant_type}")
    private String grant_type;

    public User getOpenIdAndSession(String code){
        //        拼接数据
        String param = "appid=" + appid +
                "&secret=" + secret +
                "&grant_type=" + grant_type +
                "&js_code=" + code;
//        发送请求获取OpenID和session_key
        String res= sendGet(param);
        log.info("获取用户信息"+res);
        JSONObject jsonObject = JSONObject.parseObject(res);
//        throw new AuthorizeException("没有权限");
        return new User()
                .setOpenId(jsonObject.getString("openid"))
                .setSessionKey(jsonObject.getString("session_key"))
                .setCompetence(1);
    }


    private String sendGet(String param){
        URLConnection connection;
        BufferedReader bufReader;
        StringBuilder result = new StringBuilder();
        try {
            String line;
            connection = new URL("https://api.weixin.qq.com/sns/jscode2session" +"?"+param).openConnection();
            log.info("从腾讯获取用户OpenID和session_key："+connection);
            connection.setRequestProperty("Accept-Charset","UTF-8");
            bufReader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), StandardCharsets.UTF_8));
            while ((line = bufReader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
    private JSONObject getBody(BufferedReader reader){
        StringBuilder data = new StringBuilder();
        String line;
        try {
            while (null != (line = reader.readLine())) {
                data.append(line);
            }
        } catch (IOException e) {
            log.error("请求body报错："+e);
        }finally {
            log.info("用户post上传的数据为："+data);
        }
        return JSON.parseObject(data.toString());
    }
}
