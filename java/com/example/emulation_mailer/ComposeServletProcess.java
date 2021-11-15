package com.example.emulation_mailer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ComposeServletProcess", value = "/ComposeServletProcess")
public class ComposeServletProcess extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        HttpSession hs=request.getSession(false);
        if(hs==null){
            response.sendRedirect("index.html");
        }
        else{

            String email=(String)hs.getAttribute("email");
            String to = request.getParameter("to");
            String subject=request.getParameter("subject");
            String message=request.getParameter("message");

            int status=ComposeDao.save(email,to,subject,message);
            if(status>0) {
                RequestDispatcher rd = request.getRequestDispatcher("InboxServlet");
                rd.forward(request, response);
            }
        }
    }
}
