package com.nt.red_sms_api.Util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {

    public static final String getStrTimeNowByPattern(){
        LocalDateTime currentDate = LocalDateTime.now();

        // Define the desired output format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH.mm.ss.SSSSSSSSS");

        // Format the date and time using the formatter
        String formattedCurrentDate = currentDate.format(formatter);
        return formattedCurrentDate;

    }

    public static final String getStrTimeNowByPattern(String pattern){
        LocalDateTime currentDate = LocalDateTime.now();

        // Define the desired output format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        // Format the date and time using the formatter
        String formattedCurrentDate = currentDate.format(formatter);
        return formattedCurrentDate;

    }

    public static final Timestamp getTimeStampNow(){
        Instant instant = Instant.now();
        return Timestamp.from(instant);

    }
}
