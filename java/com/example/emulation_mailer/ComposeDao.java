package com.example.emulation_mailer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ComposeDao {
    public static int save(String from, String to, String subject, String message) {
        int status=0;
        try {
            Connection con = ConProvider.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into emulation_mailer_message (Sender,Receiver,Subject,Message,Trash,MessageDate) values(?,?,?,?,?,?)");
            ps.setString(1, from);
            ps.setString(2, to);
            ps.setString(3, subject);
            ps.setString(4, message);
            ps.setString(5, "no");
            ps.setDate(6, Formatter.getCurrentDate());
            ps.executeUpdate();
            status = 1;
            return status;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return  status;
    }
}
