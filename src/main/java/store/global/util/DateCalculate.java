package store.global.util;

public class DateCalculate {

    // 날짜 문자열을 연도, 월, 일로 분리하는 메서드
    public static int[] parseDate(String date) {
        String[] parts = date.split("-");  // "-"을 기준으로 연도, 월, 일 분리
        int[] parsedDate = new int[3];  // [year, month, day]
        parsedDate[0] = Integer.parseInt(parts[0]);  // 연도
        parsedDate[1] = Integer.parseInt(parts[1]);  // 월
        parsedDate[2] = Integer.parseInt(parts[2]);  // 일
        return parsedDate;
    }

    // date1이 date2보다 이전인지 확인
    public static boolean isBefore(String date1, String date2) {
        int[] date1Parts = parseDate(date1);  // date1을 int[]로 변환
        int[] date2Parts = parseDate(date2);  // date2을 int[]로 변환

        // 연도 비교
        if (date1Parts[0] < date2Parts[0]) {
            return true;  // 연도가 더 작으면 이전 날짜
        }
        if (date1Parts[0] == date2Parts[0]) {
            // 연도가 같으면 월 비교
            if (date1Parts[1] < date2Parts[1]) {
                return true;  // 월이 더 작으면 이전 날짜
            }
            if (date1Parts[1] == date2Parts[1]) {
                // 월이 같으면 일 비교
                return date1Parts[2] < date2Parts[2];  // 일이 더 작으면 이전 날짜
            }
        }
        return false;
    }

    // date1이 date2보다 이후인지 확인
    public static boolean isAfter(String date1, String date2) {
        int[] date1Parts = parseDate(date1);  // date1을 int[]로 변환
        int[] date2Parts = parseDate(date2);  // date2을 int[]로 변환

        // 연도 비교
        if (date1Parts[0] > date2Parts[0]) {
            return true;  // 연도가 더 크면 이후 날짜
        }
        if (date1Parts[0] == date2Parts[0]) {
            // 연도가 같으면 월 비교
            if (date1Parts[1] > date2Parts[1]) {
                return true;  // 월이 더 크면 이후 날짜
            }
            if (date1Parts[1] == date2Parts[1]) {
                // 월이 같으면 일 비교
                return date1Parts[2] > date2Parts[2];  // 일이 더 크면 이후 날짜
            }
        }
        return false;
    }

    // date1이 date2와 date3 사이에 포함되는지 확인
    public static boolean isBetween(String date1, String date2, String date3) {
        // date1이 date2보다 이후이고, date1이 date3보다 이전인 경우
        return !isBefore(date1, date2) && !isAfter(date1, date3);
    }


}
