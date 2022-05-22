package com.taobaby.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author iuoily on 2022/5/21.
 */

@WebFilter("/front/*")
public class MyFrontFilter implements Filter {

    private String[] urls = {"/front/index","/front/login.page", "/front/user/login","/front/user/register","/front/register.page"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String uri = req.getRequestURI();
        for(String url: urls){
            if (url.equals(uri)) {
                filterChain.doFilter(req, resp);
                return;
            }
        }

        Object o = req.getSession().getAttribute("user");
        if (o==null) {
            resp.sendRedirect("/front/login.page");
            return;
        }

        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {}
}
