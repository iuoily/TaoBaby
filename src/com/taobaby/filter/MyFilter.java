package com.taobaby.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author iuoily on 2022/5/11.
 */

@WebFilter("/admin/*")
public class MyFilter implements Filter {

    private String[] urls = {"/admin/login/login.page", "/admin/user/login"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String uri = req.getRequestURI();
        for(String url: urls){
            if (url.equals(uri)) {
                filterChain.doFilter(req, resp);
                return;
            }
        }

        Object o = req.getSession().getAttribute("_admin");
        if (o==null) {
            resp.sendRedirect("/admin/login/login.page");
            return;
        }

        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {}
}
