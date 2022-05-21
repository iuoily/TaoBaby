package com.taobaby.dao.impl;

import com.taobaby.dao.ReceiveingAddressDao;
import com.taobaby.pojo.ReceivingAddress;
import com.taobaby.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoily on 2022/5/21.
 */
public class ReceiveingAddressDaoImpl implements ReceiveingAddressDao {

    private Connection conn;

    public ReceiveingAddressDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<ReceivingAddress> getAddressList(String userId) throws Exception {
        return JdbcUtils.getBeanList(conn, ReceivingAddress.class, "select * from s_receiving_address where user_id = ?", userId);
    }

    @Override
    public void insertAddress(ReceivingAddress R) throws SQLException {
        JdbcUtils.execute(conn,"insert  into s_receiving_address(id,receiving_address,receiving_person,mobile_phone,user_id,is_default) values (?,?,?,?,?,?)", R.getId(), R.getReceivingAddress(), R.getReceivingPerson(), R.getMobilePhone(), R.getUserId(), R.getIsDefault());
    }

    @Override
    public void updateAddress(ReceivingAddress R) throws SQLException {
        JdbcUtils.execute(conn,"update s_receiving_address set receiving_address=?,receiving_person=?,mobile_phone=?,user_id=?,is_default=? where id = ?",R.getReceivingAddress(), R.getReceivingPerson(), R.getMobilePhone(), R.getUserId(), R.getIsDefault(), R.getId());
    }

    @Override
    public void deleteAddress(String id) throws SQLException {
        JdbcUtils.execute(conn, "delete from s_receiving_address where id = ?", id);
    }

    @Override
    public void updateDefault(String id) throws SQLException {
        JdbcUtils.execute(conn, "update s_receiving_address set is_default = 1 where id = ?", id);
    }

    @Override
    public void deleteDefault() throws SQLException {
        JdbcUtils.execute(conn, "update s_receiving_address set is_default = -1");
    }
}
