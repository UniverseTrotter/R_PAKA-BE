package com.ohgiraffers.r_pakabe.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PolyTime {
    private final LocalDateTime time;

    public PolyTime(LocalDateTime time) {
        this.time = time;
    }

    public PolyTime(String time) {
        this.time = PolyTimeConverter.convertFromString(time);
    }

    public PolyTime(){
        this.time = LocalDateTime.now();
    }

    public String getRaw() {
        return PolyTimeConverter.convTimeFromDB(this.time);
    }

    public String get(){
        return PolyTimeConverter.convToStandardTime(this.time);
    }

    public String getDate(){
        return PolyTimeConverter.convDateOnly(this.time);
    }

    public String getTime(){
        return PolyTimeConverter.convTimeOnly(this.time);
    }


    public static class PolyTimeConverter {
        private static final String raw = "yyyy-MM-dd HH:mm:ss:SSS";
        private static final String standard = "yyyy-MM-dd HH:mm:ss";
        private static final String dateFormat = "yyyy-MM-dd";
        private static final String timeFormat = "HH:mm:ss";

        public static String getNow(){
            return DateTimeFormatter.ofPattern(raw).format(LocalDateTime.now());
        }

        public static String convTimeFromDB(LocalDateTime dbData){
            return convertByPattern(raw, dbData);
        }

        public static String convToStandardTime(LocalDateTime dbData){
            return convertByPattern(standard, dbData);
        }

        public static String convDateOnly(LocalDateTime dbData){
            return convertByPattern(dateFormat, dbData);
        }

        public static String convTimeOnly(LocalDateTime dbData){
            return convertByPattern(timeFormat, dbData);
        }

        private static String convertByPattern(String pattern, LocalDateTime dbData){
            return dbData == null ? null : DateTimeFormatter.ofPattern(pattern).format(dbData);
        }

        public static LocalDateTime convertFromString(String dbData){
            if (dbData == null || dbData.equals("null")){
                return null;
            }
            try {
                return LocalDateTime.parse(dbData,DateTimeFormatter.ofPattern(raw));
            } catch (DateTimeParseException e){
                try {
                    return LocalDateTime.parse(dbData,DateTimeFormatter.ofPattern(standard));
                } catch (DateTimeParseException ex) {
                    return null;
                }
            }
        }
    }
}
