package com.imooc.web.action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受参数
        String methodName=request.getParameter("method");
        //判断
        if("login".equals(methodName)){
            login(request,response);
        }

    }

    private void login(HttpServletRequest request,HttpServletResponse response){
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println(username+" "+password);
    }
}
