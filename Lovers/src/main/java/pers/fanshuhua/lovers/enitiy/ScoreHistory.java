package pers.fanshuhua.lovers.enitiy;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ScoreHistory {

  private String uuid;
  private String openId;
  private String loversOpenId;
  private long operation;
  private long score;
  private String reason;
  @JsonFormat(timezone = "GMT+8")
  private java.sql.Timestamp time;


    public ScoreHistory setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public ScoreHistory setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public ScoreHistory setLoversOpenId(String loversOpenId) {
        this.loversOpenId = loversOpenId;
        return this;
    }

    public ScoreHistory setOperation(long operation) {
        this.operation = operation;
        return this;
    }

    public ScoreHistory setScore(long score) {
        this.score = score;
        return this;
    }

    public ScoreHistory setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public ScoreHistory setTime(java.sql.Timestamp time) {
        this.time = time;
        return this;
    }


}
