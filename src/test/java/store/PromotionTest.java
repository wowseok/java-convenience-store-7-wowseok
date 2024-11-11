package store;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class PromotionTest {

//    name,buy,get,start_date,end_date
//    탄산2+1,2,1,2024-01-01,2024-12-31
//    MD추천상품,1,1,2024-01-01,2024-12-31
//    반짝할인,1,1,2024-11-01,2024-11-30

    @Test
    void name() {
        System.out.println(DateTimes.now().toLocalDate().equals("2024-11-11"));
        System.out.println((DateTimes.now()));
        System.out.println(DateTimes.now().toString());
        LocalDateTime a = LocalDateTime.of(2012, 6, 30, 12, 00);
        LocalDateTime b = LocalDateTime.of(2012, 7, 1, 12, 00);

        System.out.println(DateTimes.now().equals(DateTimes.now()));
        System.out.println(DateTimes.now().toLocalDate().equals("2024-11-01"));

        System.out.println(a);
        System.out.println(b);

        //System.out.println(Promotion.isPromotionValid());
    }
}
