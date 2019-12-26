package com.imooc.web.action;

import com.imooc.domain.User;
import com.imooc.service.UserService;
import com.imooc.service.impl.UserServiceImpl;

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
        if("logout".equals(methodName)){
            logout(request,response);
        }
    }

    private void login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println(username+" "+password);
        /*try {
            response.getWriter().println(username+password);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //数据封装
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        //处理数据
        UserService userService=new UserServiceImpl();
        User exitUser=userService.login(user);
        //根据处理结果，完成页面跳转
        if(exitUser==null){
            //登录失败
            //返回到登录页面
            request.setAttribute("msg","用户名或密码错误！");
            request.getRequestDispatcher("/admin/login.jsp").forward(request,response);
        }else{
            //登录成功
            //将用户的信息进行保存，并进行页面跳转
            request.getSession().setAttribute("exitUser",exitUser);
            response.sendRedirect(request.getContextPath()+"/admin/category_list.jsp");
        }
    }

    private void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //1.将session销毁
        request.getSession().invalidate();
        //2.页面跳转到登录界面
        response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
    }

}
