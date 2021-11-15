package com.example.emulation_mailer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "TrashServlet", value = "/TrashServlet")
public class TrashServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int Id = Integer.parseInt(request.getParameter("Id"));
        ResultSet rs;
        String email;
        HttpSession hs=request.getSession(false);
        if(hs==null){
            response.sendRedirect("index.html");
        }
        else{
            try {

                Connection con =ConProvider.getConnection();
                PreparedStatement ps =con.prepareStatement("select * from emulation_mailer_message where Id =? ");
                ps.setInt(1,Id);

                rs=ps.executeQuery();
                request.getRequestDispatcher("header.html").include(request,response);

                if(rs.next()){
                    email=(String)hs.getAttribute("email");
                    PreparedStatement ps2= con.prepareStatement("update   emulation_mailer_message set Trash=? where id=?");
                    ps2.setString(1,"yes");
                    ps2.setInt(2,Id);
                    ps2.executeUpdate();
                    //RequestDispatcher rd =request.getRequestDispatcher("InboxServlet");
                    //rd.forward(request,response);
                    response.sendRedirect("InboxServlet");

                }
            }
            catch (Exception e){
                System.out.println(e);
            }
            request.getRequestDispatcher("footer.html").include(request,response);
        }



    }

}
