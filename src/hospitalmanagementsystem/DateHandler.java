/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author oshan
 */
public class DateHandler {

    SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");

    String DateToString(Date date) {
        String stringDate = "";
        if (date != null) {
            System.out.println("date is selcted");
            stringDate = Date_Format.format(date);
        }
        return stringDate;
    }

    String getTodayDate() {
        Date date = new Date();
        String today = Date_Format.format(date);
        return today;
    }

    String getFirstOfCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int day = cal.get(Calendar.DAY_OF_MONTH);
        cal.add(Calendar.DAY_OF_MONTH, -1 * day);
        Date date = cal.getTime();
        SimpleDateFormat MY_Date_Format = new SimpleDateFormat("yyyy-MM-dd");
        return MY_Date_Format.format(date);
    }
}
