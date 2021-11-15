package com.example.emulation_mailer;

import com.mysql.cj.protocol.Resultset;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "InboxServlet", value = "/InboxServlet")
public class InboxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        request.getRequestDispatcher("link.html").include(request,response);

        HttpSession hs=request.getSession(false);
        if(hs==null){
            response.sendRedirect("index.html");
        }
        else{
            String email=(String)hs.getAttribute("email");
            //String password=(String)hs.getAttribute("password");
            out.print("<section class='sec_padding_lg'>");
            out.print("<div class='container'>");
            out.print("<span style='float:right'>Hi , "+email+"</span>");
            out.print("<h1>Inbox</h1>");
            String msg=(String)request.getAttribute("msg");
            if(msg!=null){
                out.print("<p>"+msg+"</p>");
            }
            try{
                ResultSet rs;
                Connection con =ConProvider.getConnection();
                PreparedStatement ps =con.prepareStatement("select * from emulation_mailer_message where Receiver =? and Trash='no' order by Id desc");
                ps.setString(1,email);
                rs=ps.executeQuery();


//                row start


                out.print("<div class='row'>\n" +
                        "          <table class='table'>\n" +
                        "            <thead>\n" +
                        "              <tr>\n" +
                        "                <th scope='col'>Date</th>\n" +
                        "                <th scope='col'>From</th>\n" +
                        "                <th scope='col'>Subject</th>\n" +
                        "                <th scope='col'>Description</th>\n" +
                        "              </tr>\n" +
                        "            </thead>\n" +
                        "            <tbody>");


//                messages

                while(rs.next()){

                    out.print("<tr><td>"+rs.getDate("MessageDate")+"</td><td>"+rs.getString("Sender")+"</td><td><a href='ViewMailServlet?Id="+rs.getInt("Id")+"'>"+rs.getString("Subject")+"</a></td><td><a href='ViewMailServlet?Id="+rs.getInt("Id")+"'>"+rs.getString("Message")+"</a></td></tr>");
                }



                out.print("</tbody>\n" +
                        "          </table>\n" +
                        "        </div>");
//                row closing

//                .container closing
                out.print("</div>");
                out.print("</section>");

                con.close();
            }
            catch (Exception e){
              System.out.println(e);
            }
        }
        request.getRequestDispatcher("footer.html").include(request,response);
        out.close();
    }
}
