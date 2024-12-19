package com.example.TaskManagement.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
    public static Date convertToDate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new ParseException("Wrong Format Passed: " + date, 0);
        }
    }

    public static String formatDate(Date date) throws ParseException {
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return outputFormat.format(date);
        } catch (Exception e) {
            throw new ParseException("Wrong Format Passed: " + date, 0);
        }
    }

    public static boolean checkStartEndDateValid(String startDate,String endDate) throws ParseException {
        Date mStartDate=convertToDate(startDate);
        Date mEndDate=convertToDate(endDate);
        if(mStartDate.after(mEndDate)){
            throw new IllegalArgumentException("Invalid Request: Start date cannot be greater then End Date");
        }
        return true;
    }
}
