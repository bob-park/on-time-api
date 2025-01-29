package com.malgn.ontimeapi.utils;

import java.util.List;

public interface DateUtils {

    List<String> DAYS_OF_WEEK = List.of("월", "화", "수", "목", "금", "토", "일");

    static String getDayOfWeek(int dayOfWeek) {
        return DAYS_OF_WEEK.get(dayOfWeek - 1);
    }

}
