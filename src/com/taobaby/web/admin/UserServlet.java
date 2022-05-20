package com.taobaby.web.admin;

import com.taobaby.common.BaseServlet;
import com.taobaby.pojo.Page;
import com.taobaby.pojo.User;
import com.taobaby.service.UserService;
import com.taobaby.service.impl.UserServiceImpl;
import com.taobaby.utils.EncryptionUtils;
import com.taobaby.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author iuoily on 2022/5/11.
 */
@WebServlet(value = "/admin/user/*", loadOnStartup = 0)
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /**
     * 后台用户登录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            User userByName = userService.getUserByName(username, 0);
            if (null == userByName) {
                req.setAttribute("msg", "用户不存在！");
                forward("admin/login/login.jsp", req, resp);
                return;
            }
            String msg = userService.login(username, password, 0);
            if (null != msg) {
                req.setAttribute("msg", msg);
                forward("admin/login/login.jsp", req, resp);
            } else {
                req.getSession().setAttribute("_admin", username);
                resp.sendRedirect("/admin/index/index.page");
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", e.getMessage());
            forward("admin/login/login.jsp", req, resp);
        }
    }

    /**
     * 后台用户列表
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Page<User> pageInfo = getPageInfo(req, resp);
            pageInfo = userService.getUserPage(pageInfo.getPageNum(), pageInfo.getPageSize());
            req.getSession().setAttribute("userPages", pageInfo);
            forward("/admin/user_info/list.jsp", req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errMessage", e.getMessage());
            forward("/500.jsp", req, resp);
        }
    }

    /**
     * 后台用户登出
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("_admin");
        resp.sendRedirect("/admin/login/login.page");
    }

    /**
     * 获取后台用户修改密码页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void changePasswordPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("/admin/user_info/changePassword.jsp", req, resp);
    }

    /**
     * 后台用户修改密码
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void changePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = (String) req.getSession().getAttribute("_admin");
            String oldPassword = req.getParameter("oldPassword");
            String newPassword = req.getParameter("newPassword");
            String msg = userService.changePassword(username, oldPassword, newPassword);
            if (msg != null) {
                resp.getWriter().write(msg);
                return;
            }
            resp.getWriter().write("ok");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }

    /**
     * 跳转到添加用户界面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void addPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("/admin/user_info/add.jsp", req, resp);
    }

    /**
     * 添加用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String userName = req.getParameter("userName");
            String password = req.getParameter("password");
            User user = new User(UUIDUtils.getId(), userName, EncryptionUtils.encryptMD5(password), 0);
            String msg = userService.addUser(user);
            if (null != msg) {
                resp.getWriter().write(msg);
            } else {
                resp.getWriter().write("ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }

    /**
     * 跳转到修改用户界面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void updatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            User user = userService.getUser(id);
            req.setAttribute("user", user);
            forward("/admin/user_info/update.jsp", req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errMessage", e.getMessage());
            forward("/500.jsp", req, resp);
        }

    }

    /**
     * 修改用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String id = req.getParameter("id");
            Integer type = Integer.parseInt(req.getParameter("type"));
            User user = new User(id, username, EncryptionUtils.encryptMD5(password), type);
            String msg = userService.updateUser(user);
            if (null != msg) {
                resp.getWriter().write(msg);
            } else {
                resp.getWriter().write("ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }

    /**
     * 删除用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            String msg = userService.deleteUser(id);
            if (null != msg) {
                resp.getWriter().write(msg);
            } else {
                resp.getWriter().write("ok");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }


}
