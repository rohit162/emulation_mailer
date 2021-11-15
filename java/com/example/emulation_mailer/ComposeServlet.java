package com.example.emulation_mailer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ComposeServlet", value = "/ComposeServlet")
public class ComposeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        request.getRequestDispatcher("link.html").include(request,response);
//        out.print("<section class='sec_padding_lg'>");
//        out.print("<div class='container'>");
        HttpSession hs=request.getSession(false);
        if(hs==null){
            response.sendRedirect("index.html");
        }
        else{
            String email=(String)hs.getAttribute("email");
          //  out.print("<span style='float:right'>Hi, "+email+"</span>");
//            out.print("</section>");
//            out.print("</div>");
            request.getRequestDispatcher("ComposeForm.html").include(request,response);

        }
        request.getRequestDispatcher("footer.html").include(request,response);
    }


}
