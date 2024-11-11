package store.promotion;

import camp.nextstep.edu.missionutils.DateTimes;
import store.global.util.DateCalculate;

public class Promotion {
    private String name;
    private int buy;
    private int get;
    private String startDate;
    private String endDate;

    // 생성자
    public Promotion(String name, int buy, int get, String startDate, String endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getter 메서드들
    public String getName() {
        return name;
    }

    public int getBuy() {
        return buy;
    }

    public int getGet() {
        return get;
    }

    public String getStartDate() {
        return startDate;
    }

    // 프로모션 기간 내에 현재 날짜가 포함되는지 확인

    // 날짜 비교 결과 출력
    public boolean isPromotionValid() {
        // String now = "2024-05-01";  // 기준 날짜
        // String start = "2024-01-01";  // 시작 날짜
        // String end = "2024-12-31";  // 종료 날짜
        String now = DateTimes.now().toLocalDate().toString();
        return DateCalculate.isBetween(now, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "name='" + name + '\'' +
                ", buy=" + buy +
                ", get=" + get +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}

