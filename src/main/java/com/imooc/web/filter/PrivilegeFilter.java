package com.imooc.web.filter;

import com.imooc.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(value = {"/admin/*","/CategoryServlet","/ProductServlet"})
public class PrivilegeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest=(HttpServletRequest) req;
        User existUser=(User)httpServletRequest.getSession().getAttribute("existUser");
        if(existUser==null){
            //没有登录
            req.setAttribute("msg","您还没有登录,没有权限访问");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
