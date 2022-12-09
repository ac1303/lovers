package pers.fanshuhua.lovers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.fanshuhua.lovers.dao.ScoreHistoryDao;
import pers.fanshuhua.lovers.enitiy.ScoreHistory;

import java.sql.Timestamp;
import java.util.UUID;

@SpringBootTest
class LoversApplicationTests {
    @Autowired
    private ScoreHistoryDao scoreHistoryDao;

    @Test
    void contextLoads() {
//        scoreHistoryDao.insertScoreHistory(new ScoreHistory()
//                .setUuid(UUID.randomUUID().toString())
//                .setOpenId("okq_s4kcJyr8DJLsOkFKYPsVcM8I")
//                .setLoversOpenId("okq_s4kys25d_A6e8w4KgkKE4qqY")
//                .setOperation(1)
//                .setScore(222)
//                .setReason("sss")
//                .setTime(new Timestamp(System.currentTimeMillis()))
//        );
    }

    @Test
    void testGetScoreHistoryList() {
        System.out.println(scoreHistoryDao.getScoreHistory(0, 10));
    }
}
