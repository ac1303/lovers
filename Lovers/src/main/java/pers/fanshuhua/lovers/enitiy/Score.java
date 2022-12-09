package pers.fanshuhua.lovers.enitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 小王的饭饭
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Score {

  private String openId;
  private String loveOpenId;
  private long score;
  private long gold;


    public Score setOpenid(String openid) {
        this.openId = openid;
        return this;
    }

    public Score setLoveOpenId(String loveOpenId) {
        this.loveOpenId = loveOpenId;
        return this;
    }

    public Score setScore(long score) {
        this.score = score;
        return this;
    }

    public Score setGold(long gold) {
        this.gold = gold;
        return this;
    }

}
