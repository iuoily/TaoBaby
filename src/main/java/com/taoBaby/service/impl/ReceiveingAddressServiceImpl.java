package com.taoBaby.service.impl;

import com.taoBaby.dao.ReceiveingAddressDao;
import com.taoBaby.dao.impl.ReceiveingAddressDaoImpl;
import com.taoBaby.pojo.ReceivingAddress;
import com.taoBaby.service.ReceiveingAddressService;
import com.taoBaby.utils.DBUtils;
import com.taoBaby.utils.SpringUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoily on 2022/5/21.
 */
public class ReceiveingAddressServiceImpl implements ReceiveingAddressService {

    private ReceiveingAddressDao receiveingAddressDao = null;

    @Override
    public List<ReceivingAddress> listAddress(String userId) throws Exception {
        Connection conn = DBUtils.getConn();
        receiveingAddressDao = SpringUtils.getBean(ReceiveingAddressDao.class);
        List<ReceivingAddress> addressList = receiveingAddressDao.getAddressList(userId);
        DBUtils.close(conn);
        return addressList;
    }

    @Override
    public void addAddress(ReceivingAddress receivingAddress) throws SQLException {
        Connection conn = DBUtils.getConn();
        receiveingAddressDao = SpringUtils.getBean(ReceiveingAddressDao.class);
        receiveingAddressDao.insertAddress(receivingAddress);
        DBUtils.close(conn);
    }

    @Override
    public void removeAddress(String id) throws SQLException {
        Connection conn = DBUtils.getConn();
        receiveingAddressDao = SpringUtils.getBean(ReceiveingAddressDao.class);
        receiveingAddressDao.deleteAddress(id);
        DBUtils.close(conn);
    }

    @Override
    public void modifyAddress(ReceivingAddress receivingAddress) throws SQLException {
        Connection conn = DBUtils.getConn();
        receiveingAddressDao = SpringUtils.getBean(ReceiveingAddressDao.class);
        receiveingAddressDao.updateAddress(receivingAddress);
        DBUtils.close(conn);
    }

    @Override
    public void modifyDefault(String id) {
            Connection conn = null;
        try {
            conn = DBUtils.getConn();
            conn.setAutoCommit(false);
            receiveingAddressDao = SpringUtils.getBean(ReceiveingAddressDao.class);
            receiveingAddressDao.deleteDefault();
            receiveingAddressDao.updateDefault(id);
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            DBUtils.close(conn);
        }
    }
}
