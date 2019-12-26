package com.imooc.web.action;

import com.imooc.domain.Category;
import com.imooc.service.CategoryService;
import com.imooc.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodName=request.getParameter("method");
        if("findAll".equals(methodName)){
            //查询所有分类
            findAll(request,response);
        }

    }

    /**
     * 查询所有分类的方法
     * @param request
     * @param response
     */
    private void findAll(HttpServletRequest request, HttpServletResponse response) {
        //接收参数
        //封装数据
        //调用业务层处理数据
        CategoryService categoryService=new CategoryServiceImpl();
        List<Category> list=categoryService.findAll();
        for (Category c:list){
            System.out.println(c);
        }
        //页面跳转
    }
}
