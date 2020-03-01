/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author A
 */
public class DateConverter {
    public String getMySqlDateNow() {
        Date now = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String mysqlDateString = formatter.format(now);
        return mysqlDateString;
    }
}
