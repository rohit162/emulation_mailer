package com.example.emulation_mailer;
import com.example.emulation_mailer.RegisterDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out= response.getWriter();

          String name,email,password,gender,dob,addressLine,city,state,country,contact;
          name=request.getParameter("name");
          email=request.getParameter("email");
          password=request.getParameter("password");
          gender=request.getParameter("gender");
          dob=request.getParameter("dob");
          addressLine=request.getParameter("addressLine");
          city=request.getParameter("city");
          state=request.getParameter("state");
          country=request.getParameter("country");
          contact=request.getParameter("contact");
          int status= RegisterDao.save(name,email+"@emulation.com",password,gender,dob,addressLine,city,state,country,contact);
          if(status>0){
            //  out.println("<h1>Record saved</h1>");
            RequestDispatcher rd =request.getRequestDispatcher("InboxServlet");
            rd.include(request,response);
          }
          else{
              RequestDispatcher rd =request.getRequestDispatcher("error.html");
              rd.forward(request,response);

          }
    }
}
