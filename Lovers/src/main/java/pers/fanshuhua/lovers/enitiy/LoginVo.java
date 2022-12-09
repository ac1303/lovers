package pers.fanshuhua.lovers.enitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 小王的饭饭
 * @create 2022/11/26 16:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginVo {
    private User user;
    private Lovers lovers;
    private Score score;
}
