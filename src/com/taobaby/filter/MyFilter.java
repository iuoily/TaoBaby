//package com.taobaby.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author iuoily on 2022/5/11.
// */
//
//@WebFilter
//public class MyFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//        req.setCharacterEncoding("utf-8");
//        resp.setCharacterEncoding("utf-8");
//        filterChain.doFilter(req,resp);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
