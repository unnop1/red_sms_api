package com.nt.red_sms_api.Util;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.nt.red_sms_api.entity.view.sms_gateway.date.ByOrderType;
import com.nt.red_sms_api.entity.view.sms_gateway.date.BySending;
import com.nt.red_sms_api.entity.view.sms_gateway.month.ByConditionMonth;
import com.nt.red_sms_api.entity.view.sms_gateway.month.ByOrderTypeMonth;
import com.nt.red_sms_api.entity.view.sms_gateway.month.BySendingMonth;

public class ManualSort {

    private static final Map<String, Month> MONTH_MAP = createMonthMap();

    private static Map<String, Month> createMonthMap() {
        Map<String, Month> map = new HashMap<>();
        for (Month month : Month.values()) {
            map.put(month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase(), month);
        }
        return map;
    }

    public static void sortByOrderTypeDate(List<ByOrderType> list, String sortName, String sortBy) {
        Comparator<ByOrderType> comparator;

        switch (sortName.toLowerCase()) {
            case "totalevent":
                comparator = Comparator.comparingInt(ByOrderType::getTOTALEVENT);
                break;
            case "totalunmatch":
                comparator = Comparator.comparingInt(ByOrderType::getTOTALUNMATCH);
                break;
            case "totalsuccess":
                comparator = Comparator.comparingInt(ByOrderType::getTOTALSUCCESS);
                break;
            default:
                throw new IllegalArgumentException("Invalid sort field: " + sortName);
        }

        if ("desc".equalsIgnoreCase(sortBy)) {
            comparator = comparator.reversed();
        }

        list.sort(comparator);
    }

    public static void sortByOrderTypeMonth(List<ByOrderTypeMonth> list, String sortName, String sortBy) {
        Comparator<ByOrderTypeMonth> comparator;

        switch (sortName.toLowerCase()) {
            case "totalevent":
                comparator = Comparator.comparingInt(ByOrderTypeMonth::getTOTALEVENT);
                break;
            case "totalunmatch":
                comparator = Comparator.comparingInt(ByOrderTypeMonth::getTOTALUNMATCH);
                break;
            case "totalsuccess":
                comparator = Comparator.comparingInt(ByOrderTypeMonth::getTOTALSUCCESS);
                break;
            default:
                throw new IllegalArgumentException("Invalid sort field: " + sortName);
        }

        if ("desc".equalsIgnoreCase(sortBy)) {
            comparator = comparator.reversed();
        }

        list.sort(comparator);
    }

    public  static void sortByConditionMonth(List<ByConditionMonth> list, String sortName, String sortBy) {
        Comparator<ByConditionMonth> comparator;

        switch (sortName.toLowerCase()) {
            case "totalevent":
                comparator = Comparator.comparingInt(ByConditionMonth::getTOTALEVENT);
                break;
            case "totalunmatch":
                comparator = Comparator.comparingInt(ByConditionMonth::getTOTALUNMATCH);
                break;
            case "totalsuccess":
                comparator = Comparator.comparingInt(ByConditionMonth::getTOTALSUCCESS);
                break;
            default:
                throw new IllegalArgumentException("Invalid sort field: " + sortName);
        }

        if ("desc".equalsIgnoreCase(sortBy)) {
            comparator = comparator.reversed();
        }

        list.sort(comparator);
    }

    public  static void sortBySendingMonth(List<BySendingMonth> list, String sortName, String sortBy) {
        Comparator<BySendingMonth> comparator;

        switch (sortName.toLowerCase()) {
            case "totalevent":
                comparator = Comparator.comparingInt(BySendingMonth::getTOTALSEND);
                break;
            case "totalsuccess":
                comparator = Comparator.comparingInt(BySendingMonth::getTOTALSUCCESS);
                break;
            case "totalfail":
                comparator = Comparator.comparingInt(BySendingMonth::getTOTALFAIL);
                break;
            default:
                throw new IllegalArgumentException("Invalid sort field: " + sortName);
        }

        if ("desc".equalsIgnoreCase(sortBy)) {
            comparator = comparator.reversed();
        }

        list.sort(comparator);
    }

    public static void sortBySendingDate(List<BySending> list, String sortName, String sortBy) {
        Comparator<BySending> comparator;

        switch (sortName.toLowerCase()) {
            case "totalsend":
                comparator = Comparator.comparingInt(BySending::getTOTALSEND);
                break;
            case "totalsuccess":
                comparator = Comparator.comparingInt(BySending::getTOTALSUCCESS);
                break;
            case "totalfail":
                comparator = Comparator.comparingInt(BySending::getTOTALFAIL);
                break;
            case "date_only":
                comparator = Comparator.comparing(bySending -> 
                    LocalDate.of(
                        Integer.parseInt(bySending.getYEAR_ONLY()), 
                        MONTH_MAP.get(bySending.getMONTH_ONLY().toUpperCase()), 
                        Integer.parseInt(bySending.getDATE_ONLY())
                    )
                );
                break;
            default:
                throw new IllegalArgumentException("Invalid sort field: " + sortName);
        }

        if ("desc".equalsIgnoreCase(sortBy)) {
            comparator = comparator.reversed();
        }

        list.sort(comparator);
    }
}
