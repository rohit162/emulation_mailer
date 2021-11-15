package com.example.emulation_mailer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "SentServlet", value = "/SentServlet")
public class SentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");
        ResultSet rs;
        String email;
        PrintWriter out = response.getWriter();
        HttpSession hs=request.getSession(false);
        if(hs==null){
            response.sendRedirect("index.html");
        }
        else{

            request.getRequestDispatcher("link.html").include(request,response);
            out.print("<section class='sec_padding_lg'>");
            out.print("<div class='container'>");
            email=(String)hs.getAttribute("email");
            out.print("<span style='float:right'>Hi , "+email+"</span>");
            out.print("<h1>Sent Mail</h1>");
            try {
                Connection con =ConProvider.getConnection();
                PreparedStatement ps =con.prepareStatement("select * from emulation_mailer_message where Sender =? and Trash=? ");
                ps.setString(1,email);
                ps.setString(2,"no");
                rs=ps.executeQuery();
                out.print("<div class='row'>\n" +
                        "          <table class='table'>\n" +
                        "            <thead>\n" +
                        "              <tr>\n" +
                        "                <th scope='col'>Date</th>\n" +
                        "                <th scope='col'>Receiver</th>\n" +
                        "                <th scope='col'>Subject</th>\n" +
                        "                <th scope='col'>Description</th>\n" +
                        "              </tr>\n" +
                        "            </thead>\n" +
                        "            <tbody>");


//                messages

                while(rs.next()){

                    out.print("<tr><td>"+rs.getDate("MessageDate")+"</td><td>"+rs.getString("Receiver")+"</td><td><a href='ViewMailServlet?Id="+rs.getInt("Id")+"'>"+rs.getString("Subject")+"</a></td><td><a href='ViewMailServlet?Id="+rs.getInt("Id")+"'>"+rs.getString("Message")+"</a></td></tr>");
                }



                out.print("</tbody>\n" +
                        "          </table>\n" +
                        "        </div>");
//                out.print("<table border='1' style='width:700px;'>");
//                out.print("<tr style='background-color:grey;color:white'><td>Receiver</td><td>Subject</td></tr>");
//                while(rs.next()){
//                    out.print("<tr><td>"+rs.getString("Receiver")+"</td><td><a href='ViewSentMailServlet?Id="+rs.getInt("Id")+"'>"+rs.getString("Subject")+"</a></td></tr>");
//                }
//                out.print("</table>");
//                out.print("</div>");
//                out.print("</section>");
            }
            catch (Exception e){
                System.out.println(e);
            }
            request.getRequestDispatcher("footer.html").include(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
