package com.example.emulation_mailer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "ViewSentMailServlet", value = "/ViewSentMailServlet")
public class ViewSentMailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int Id = Integer.parseInt(request.getParameter("Id"));
        ResultSet rs;
        String email;
        response.setContentType("text/html");
        PrintWriter out =response.getWriter();
       // request.getRequestDispatcher("header.html").include(request, response);
        request.getRequestDispatcher("link.html").include(request, response);

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
                if(rs.next()){
                    out.println("<section class='mail-page' id='content'>\n" +
                            "        <div class='container'>\n" +
                            "            <div class='row'>\n" +
                            "                <div class='col-md-3'></div>\n" +
                            "                <div class='col-md-6'>\n" +
                            "                    <div class='card' style='width: 100%;'>\n" +
                            "                        <div class='card-header'>\n" +
                            "                            <h5 class='card-title mt-2'> From: "+rs.getString("Receiver") + " </h5>\n" +
                            "                        </div>\n" +
                            "                        <div class='card-body'>\n" +
                            "                            <h6 class='card-subtitle mb-2 text-muted'>Subject: "+rs.getString("Subject") +"</h6>\n" +
                            "                            <blockquote class='card-text py-4 px-3'> "+rs.getString("Message") +"</blockquote>\n" +
                            "                        </div>\n" +
                            "                        <div class='card-footer mail-actions d-flex justify-content-between'>\n" +
                           // "                            <a href='ComposeForm.html' class='card-link btn text-info py-1'><i class='fas fa-reply'></i> Reply</a>\n" +
                            "                            <a href='TrashServlet?Id="+Id+"' class='card-link btn text-danger py-1'><i class='fas fa-trash'></i> Move to trash</a>\n" +
                            "                        </div>\n" +
                            "                    </div>\n" +
                            "                </div>\n" +
                            "                <div class='col-md-3'></div>\n" +
                            "            </div>\n" +
                            "        </div>\n" +
                            "    </section>");
//                    email=(String)hs.getAttribute("email");
//                    out.println(rs.getString("Message"));
//                    out.println("<br><a href='TrashServlet?Id="+Id+"'>Trash</a>");
                }
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
