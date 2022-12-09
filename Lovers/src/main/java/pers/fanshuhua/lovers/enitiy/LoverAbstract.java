package pers.fanshuhua.lovers.enitiy;

import lombok.Data;

/**
 * @author 小王的饭饭
 * @create 2022/12/4 13:35
 */
@Data
abstract class LoverAbstract {
    protected String openId;
    protected String loversOpenId;

    public LoverAbstract setLoversOpenId(String loversOpenId) {
        this.loversOpenId = loversOpenId;
        return this;
    }

    public LoverAbstract setOpenId(String openId) {
        this.openId = openId;
        return this;
    }
}
