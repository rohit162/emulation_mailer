package com.example.emulation_mailer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDao {
    public static int check(String email, String password) {

            int status=0;
            ResultSet rs;
            try {
                Connection con=ConProvider.getConnection();
                PreparedStatement ps=con.prepareStatement("select * from emulation_mailer_user where email =? and password=?");
                ps.setString(1,email);
                ps.setString(2,password);
                rs= ps.executeQuery();
                if(rs.next()){
                    status=1;
                }

            }
            catch (Exception e){
                System.out.println(e);
            }
            System.out.print("hiiiiii    "+status);
            return status;
        }


    }

