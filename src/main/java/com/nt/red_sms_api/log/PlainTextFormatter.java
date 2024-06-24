package com.nt.red_sms_api.log;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class PlainTextFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        sb.append(record.getMessage());
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
