package com.example.test1.config;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Config {
    public static final String BOOTSTRAP_SERVERS = "localhost:9092";
    public static final String PRODUCT_TOPIC = "ProductTopic";
    public static final String BULK_PRODUCT_TOPIC = "BulkProductTopic";

    public static final int BATCH_SIZE = 1000;
    public static int PRINT_COUNT = 0;

    public static void printTime(String message, int count) {
        PRINT_COUNT += count;
        if (PRINT_COUNT >= Config.BATCH_SIZE) {
            LocalTime now = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
            String formatedNow = now.format(formatter);

            System.out.println(message + " CNT:" + PRINT_COUNT + " - Time : " + formatedNow);
            PRINT_COUNT -= Config.BATCH_SIZE;
        }
    }

}
