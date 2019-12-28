package com.imooc.web.action;

import com.imooc.domain.Category;
import com.imooc.domain.Product;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import com.imooc.service.impl.CategoryServiceImpl;
import com.imooc.service.impl.ProductServiceImpl;
import com.imooc.utils.UploadUtils;
import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodName=request.getParameter("method");
        if("findAll".equals(methodName)){
            findAll(request,response);
        }else if("saveUI".equals(methodName)){
            //跳转到产品添加
            saveUI(request,response);
        }else if("save".equals(methodName)) {
            //添加产品
            save(request, response);
        }else if("edit".equals(methodName)) {
            //产品修改
            edit(request, response);
        }else if("update".equals(methodName)) {
            //产品修改确认
            update(request, response);
        }else if("delete".equals(methodName)) {
            //产品删除
            delete(request, response);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //接收数据
        Integer pid=Integer.parseInt(request.getParameter("pid"));
        //封装数据
        //业务层处理
        ProductService productService=new ProductServiceImpl();
        Product product=productService.findOne(pid);
        String path=product.getPath();
        if(StringUtils.isNullOrEmpty(path)){
            //获取绝对路径
            String realPath=this.getServletContext().getRealPath(path);
            File file=new File(realPath);
            if(file.exists()){
                file.delete();
            }
        }
        productService.delete(pid);
        //处理结果
        response.sendRedirect(request.getContextPath()+"/ProductServlet?method=findAll");
    }

    /**
     * 修改商品
     * @param request
     * @param response
     */
    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //接收数据
        Map<String,String> map=UploadUtils.uploadFile(request);
        //封装数据
        Product product=new Product();
        product.setPid(Integer.parseInt(map.get("pid")));
        product.setPname(map.get("pname"));
        product.setAuthor(map.get("author"));
        product.setPrice(Double.parseDouble(map.get("price")));
        product.setDescription(map.get("description"));
        product.setFilename(map.get("filename"));
        product.setPath(map.get("path"));
        product.getCategory().setCid(Integer.parseInt(map.get("cid")));
        //调用业务层
        ProductService productService=new ProductServiceImpl();
        productService.update(product);
        //页面跳转
        response.sendRedirect(request.getContextPath()+"/ProductServlet?method=findAll");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pid=Integer.parseInt(request.getParameter("pid"));
        ProductService productService=new ProductServiceImpl();
        Product product=productService.findOne(pid);
        System.out.println(product);
        CategoryService categoryService=new CategoryServiceImpl();
        List<Category> categoryList=categoryService.findAll();
        request.setAttribute("product",product);
        request.setAttribute("categoryList",categoryList);
        request.getRequestDispatcher("/admin/product_update.jsp").forward(request,response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("save方法执行了");
        //文件上传
        Map<String,String> map=UploadUtils.uploadFile(request);
        //封装数据
        Product product=new Product();
        product.setPname(map.get("pname"));
        product.setAuthor(map.get("author"));
        product.setPrice(Double.parseDouble(map.get("price")));
        product.setDescription(map.get("description"));
        product.setFilename(map.get("filename"));
        product.setPath(map.get("path"));
        product.getCategory().setCid(Integer.parseInt(map.get("cid")));
        //调用业务层
        ProductService productService=new ProductServiceImpl();
        productService.save(product);
        //处理结束跳转到查询页
        response.sendRedirect(request.getContextPath()+"/ProductServlet?method=findAll");
    }

    private void saveUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryService categoryService=new CategoryServiceImpl();
        List<Category> categoryList=categoryService.findAll();
        request.setAttribute("categoryList",categoryList);
        request.getRequestDispatcher("/admin/product_add.jsp").forward(request,response);
    }

    /**
     * 查询所有产品
     * @param request
     * @param response
     */
    private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //获取参数
        //封装数据
        //调用service
        ProductService productService=new ProductServiceImpl();
        List<Product> list=productService.findAll();
        //处理结果
        request.setAttribute("list",list);
        request.getRequestDispatcher("/admin/product_list.jsp").forward(request,response);
    }
}
