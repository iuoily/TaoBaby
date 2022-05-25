package com.taobaby.service.impl;

import com.taobaby.dao.ReceiveingAddressDao;
import com.taobaby.dao.impl.ReceiveingAddressDaoImpl;
import com.taobaby.pojo.ReceivingAddress;
import com.taobaby.service.ReceiveingAddressService;
import com.taobaby.utils.DBUtils;

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
        receiveingAddressDao = new ReceiveingAddressDaoImpl(conn);
        List<ReceivingAddress> addressList = receiveingAddressDao.getAddressList(userId);
        DBUtils.close(conn);
        return addressList;
    }

    @Override
    public void addAddress(ReceivingAddress receivingAddress) throws SQLException {
        Connection conn = DBUtils.getConn();
        receiveingAddressDao = new ReceiveingAddressDaoImpl(conn);
        receiveingAddressDao.insertAddress(receivingAddress);
        DBUtils.close(conn);
    }

    @Override
    public void removeAddress(String id) throws SQLException {
        Connection conn = DBUtils.getConn();
        receiveingAddressDao = new ReceiveingAddressDaoImpl(conn);
        receiveingAddressDao.deleteAddress(id);
        DBUtils.close(conn);
    }

    @Override
    public void modifyAddress(ReceivingAddress receivingAddress) throws SQLException {
        Connection conn = DBUtils.getConn();
        receiveingAddressDao = new ReceiveingAddressDaoImpl(conn);
        receiveingAddressDao.updateAddress(receivingAddress);
        DBUtils.close(conn);
    }

    @Override
    public void modifyDefault(String id) {
            Connection conn = null;
        try {
            conn = DBUtils.getConn();
            conn.setAutoCommit(false);
            receiveingAddressDao = new ReceiveingAddressDaoImpl(conn);
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
