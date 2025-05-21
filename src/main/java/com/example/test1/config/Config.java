package com.example.test1.config;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Config {
    public static final String BOOTSTRAP_SERVERS = "localhost:9092";
    public static final String PRODUCT_TOPIC = "ProductTopic";
    public static final String BULK_PRODUCT_TOPIC = "BulkProductTopic";

    public static final int BATCH_SIZE = 1000;
    public static boolean START_FLAG = true;

    public static void printTime(String message, int count) {
        System.out.println(message + " CNT:" + count + " - Time : " + getFormatedTime());
    }

    private static String getFormatedTime() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
        return now.format(formatter);
    }

}
