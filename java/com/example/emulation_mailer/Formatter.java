package com.example.emulation_mailer;

import java.sql.Date;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Formatter {
    public static Date getsqlDate(String dob) {
        java.sql.Date d=null;
        try{
            SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dt=f.parse(dob);
            d=new java.sql.Date(dt.getTime());

        }
        catch(ParseException e){
            System.out.println(e);

        }
        return d;

    }

    public static Date getCurrentDate() {
        java.sql.Date d=null;
        try{
            java.util.Date dt=java.util.Calendar.getInstance().getTime();
            d=new java.sql.Date(dt.getTime());
        }
        catch (Exception e){

        }
        return d;
    }
}
