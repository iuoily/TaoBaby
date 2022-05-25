package com.taobaby.web.front;

import com.mysql.cj.util.StringUtils;
import com.taobaby.common.BaseServlet;
import com.taobaby.pojo.ReceivingAddress;
import com.taobaby.pojo.User;
import com.taobaby.service.ReceiveingAddressService;
import com.taobaby.service.impl.ReceiveingAddressServiceImpl;
import com.taobaby.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoly 2022/05/21
 */
@WebServlet("/front/receiveingAddress/*")
public class ReceiveingAddressServlet extends BaseServlet {

    private ReceiveingAddressService receiveingAddressService = new ReceiveingAddressServiceImpl();

    /**
     * 跳转到收货地址页
     */
    public void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = (User) req.getSession().getAttribute("user");
            List<ReceivingAddress> receivingAddresses = receiveingAddressService.listAddress(user.getId());
            req.getSession().setAttribute("receivingAddressesList", receivingAddresses);
        } catch (Exception e) {
            e.printStackTrace();
        }
        forward("/front/receiving_address/deliverAddress.jsp", req, resp);
    }

    /**
     * 保存收货地址(id == null ？ 添加 ：修改)
     */
    public void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String receivingAddress = req.getParameter("receivingAddress");
            String receivingPerson = req.getParameter("receivingPerson");
            long mobilePhone = Long.parseLong(req.getParameter("mobilePhone"));
            int flag = req.getParameter("isDefault") == null ? -1 : 1;
            User user = (User) req.getSession().getAttribute("user");
            String id = req.getParameter("id");
            if (StringUtils.isNullOrEmpty(id)) {
                List<ReceivingAddress> list = (List<ReceivingAddress>) req.getSession().getAttribute("receivingAddressesList");
                if (list.size() >= 5) {
                    resp.getWriter().write("只能保存5个地址");
                    return;
                }
                id = UUIDUtils.getId();
                receiveingAddressService.addAddress(new ReceivingAddress(id, receivingAddress, receivingPerson, mobilePhone, user.getId(), flag));
            } else {
                receiveingAddressService.modifyAddress(new ReceivingAddress(id, receivingAddress, receivingPerson, mobilePhone, user.getId(), flag));
            }
            if (flag == 1) {
                receiveingAddressService.modifyDefault(id);
            }
            resp.getWriter().write("ok");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("输入不合法！");
        }
    }

    /**
     * 删除收货地址
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            receiveingAddressService.removeAddress(id);
            resp.getWriter().write("ok");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }

    /**
     * 修改默认收货地址
     */
    public void setDefault(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            receiveingAddressService.modifyDefault(id);
            resp.getWriter().write("ok");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }
}
