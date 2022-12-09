package pers.fanshuhua.lovers.enitiy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

  private String openId;
  private String sessionKey;
  private long competence;


  public User setOpenId(String openId) {
    this.openId = openId;
    return this;
  }

    public User setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
        return this;
    }

    public User setCompetence(long competence) {
        this.competence = competence;
        return this;
    }

}
