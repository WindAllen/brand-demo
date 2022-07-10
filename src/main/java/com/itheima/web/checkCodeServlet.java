package com.itheima.web;

import com.itheima.utils.CheckCodeUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/checkCodeServlet")
public class checkCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream fos = response.getOutputStream();
        String checkcode = CheckCodeUtil.outputVerifyImage(100, 50, fos, 4);
        HttpSession session = request.getSession();
        session.setAttribute("checkcodeGen",checkcode);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
