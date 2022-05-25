package com.taobaby.common;

import com.mysql.cj.util.StringUtils;
import com.taobaby.pojo.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author iuoily on 2022/5/11.
 */


public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String methodName = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1);
        try {
            Class<? extends BaseServlet> aClass = this.getClass();
            Method method = aClass.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("未找到该方法");
            e.printStackTrace();
        }
    }

    /**
     *
     * @param url 访问链接
     * @param req 请求
     * @param resp 响应
     * @throws ServletException
     * @throws IOException
     */
    protected void forward(String url, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/" + url).forward(req, resp);
    }

    /**
     * 获取分页数据
     * @param req req
     * @param resp resp
     * @param <T> 分页数据类型
     * @return 分页数据
     */
    protected <T> Page<T> getPageInfo(HttpServletRequest req, HttpServletResponse resp) {
        String pageNum = req.getParameter("pageNum");
        String pageSize = req.getParameter("pageSize");

        Page<T> page = new Page<>();
        if (!StringUtils.isNullOrEmpty(pageNum)) {
            page.setPageNum(Integer.parseInt(pageNum));
        } else {
            page.setPageNum(1);
        }
        if (!StringUtils.isNullOrEmpty(pageSize)) {
            page.setPageSize(Integer.parseInt(pageSize));
        } else {
            page.setPageSize(10);
        }
        return page;
    }

}
