package com.itheima.web;

import com.itheima.pojo.User;
import com.itheima.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCodeuser = request.getParameter("checkCode");
        System.out.println("输入的"+checkCodeuser);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        boolean p = userService.register(user);
        HttpSession session = request.getSession();
        String checkcodeGen = (String) session.getAttribute("checkcodeGen");
        System.out.println("yanz"+checkcodeGen);
        if(!checkCodeuser.equalsIgnoreCase(checkcodeGen)){
            request.setAttribute("register_message","验证码错误");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }
        if (p==true){
            request.setAttribute("register_message","注册成功，请登录");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }else{
            request.setAttribute("register_message","用户名已经存在");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
