package com.example.emulation_mailer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            PrintWriter out = response.getWriter();
            String email,password;
            int status;
            email = request.getParameter("email");
            password = request.getParameter("password");
            status=LoginDao.check(email,password);
        if(status>0){
            request.getSession().setAttribute("login","true");
            request.getSession().setAttribute("email",email);
            RequestDispatcher rd =request.getRequestDispatcher("InboxServlet");
            rd.forward(request,response);
        }
        else{
            RequestDispatcher rd =request.getRequestDispatcher("error.html");
            rd.forward(request,response);

        }
    }
}
