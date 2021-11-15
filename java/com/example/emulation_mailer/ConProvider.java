package com.example.emulation_mailer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static java.lang.Class.forName;

public class ConProvider{
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emulation_mailer","root","");

        }
        catch (Exception e){
          System.out.println(e);

        }
        return con;
    }
}
