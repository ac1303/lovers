package pers.fanshuhua.lovers.enitiy;

import lombok.*;

/**
 * @author 小王的饭饭
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Lovers extends LoverAbstract{

  private java.sql.Timestamp dateOfLove;

    public Lovers setDateOfLove(java.sql.Timestamp dateOfLove) {
        this.dateOfLove = dateOfLove;
        return this;
    }
    @Override
    public Lovers setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    @Override
    public Lovers setLoversOpenId(String loversOpenId) {
        this.loversOpenId = loversOpenId;
        return this;
    }

}
