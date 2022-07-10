package com.itheima.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String u = req.getRequestURL().toString();
        String[] urls = {"/login.jsp","/css/","/imgs/","/register.jsp","/loginServlet","/registerServlet","checkCodeServlet"};

        for (String url : urls) {
            if (u.contains(url)){
                chain.doFilter(request, response);
                return;
            }
        }
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        if (user != null) {
            chain.doFilter(request, response);
        } else {
            req.setAttribute("login_message", "您还未登录！");
            req.getRequestDispatcher("/login.jsp").forward(req, response);
        }
    }
}
