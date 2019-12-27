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
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodName=request.getParameter("method");
        if("findAll".equals(methodName)){
            //查询所有分类
            findAll(request,response);
        }else if("saveUI".equals(methodName)){
            //跳转到分类添加
            saveUI(request,response);
        }else if("save".equals(methodName)) {
            //分类添加
            save(request, response);
        }else if("edit".equals(methodName)) {
            //分类修改默认数据
            edit(request, response);
        }else if("update".equals(methodName)) {
            //分类修改确认
            update(request, response);
        }else if("delete".equals(methodName)) {
            //分类删除
            delete(request, response);
        }

    }

    /**
     * 删除分类
     * @param request
     * @param response
     */

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer cid=Integer.parseInt(request.getParameter("cid"));
        CategoryService categoryService=new CategoryServiceImpl();
        categoryService.delete(cid);
        //处理结果
        response.sendRedirect(request.getContextPath()+"/CategoryServlet?method=findAll");
    }

    /**
     * 分类管理-确认修改
     * @param request
     * @param response
     */
    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer cid=Integer.parseInt(request.getParameter("cid"));
        String cname=request.getParameter("cname");
        String cdesc=request.getParameter("cdesc");
        //封装数据
        Category category=new Category();
        category.setCid(cid);
        category.setCname(cname);
        category.setCdesc(cdesc);
        //业务层处理数据
        CategoryService categoryService=new CategoryServiceImpl();
        categoryService.update(category);
        //处理结果
        response.sendRedirect(request.getContextPath()+"/CategoryServlet?method=findAll");
    }

    /**
     * 修改分类查询默认数据，页面跳转
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer cid=Integer.parseInt(request.getParameter("cid"));
        CategoryService categoryService=new CategoryServiceImpl();
        Category category=categoryService.findOne(cid);
        //页面跳转
        request.setAttribute("category",category);
        request.getRequestDispatcher("/admin/category_update.jsp").forward(request,response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //接收数据
        String cname=request.getParameter("cname");
        String cdesc=request.getParameter("cdesc");
        System.out.println(cname+" "+cdesc);
        //封装数据
        Category category=new Category();
        category.setCname(cname);
        category.setCdesc(cdesc);
        //调用业务层处理数据
        CategoryService categoryService=new CategoryServiceImpl();
        categoryService.save(category);
        //处理结果
        response.sendRedirect(request.getContextPath()+"/CategoryServlet?method=findAll");
    }

    private void saveUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin/category_add.jsp").forward(request,response);
    }

    /**
     * 查询所有分类的方法
     * @param request
     * @param response
     */
    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        //封装数据
        //调用业务层处理数据
        CategoryService categoryService=new CategoryServiceImpl();
        List<Category> list=categoryService.findAll();
        //页面跳转
        request.setAttribute("list",list);
        request.getRequestDispatcher("/admin/category_list.jsp").forward(request,response);
    }
}
