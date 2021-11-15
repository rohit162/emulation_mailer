package com.example.emulation_mailer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet(name = "DeleteServlet", value = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int Id=Integer.parseInt(request.getParameter("Id"));
        HttpSession hs=request.getSession(false);
        if(hs==null){
            response.sendRedirect("index.html");
        }
        else {
         try{
             Connection con = ConProvider.getConnection();
             PreparedStatement ps = con.prepareStatement("delete from emulation_mailer_message where Id=?");
             ps.setInt(1, Id);
             ps.executeUpdate();
             RequestDispatcher rd=request.getRequestDispatcher("InboxServlet");
             rd.forward(request,response);
         }
            catch (Exception e){
             System.out.println(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
