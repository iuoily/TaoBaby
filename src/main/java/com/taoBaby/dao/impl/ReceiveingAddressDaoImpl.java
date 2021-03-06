package com.taoBaby.dao.impl;

import com.taoBaby.dao.ReceiveingAddressDao;
import com.taoBaby.pojo.ReceivingAddress;
import com.taoBaby.utils.DBUtils;

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
        return DBUtils.getBeanList(conn, ReceivingAddress.class, "select * from s_receiving_address where user_id = ? order by is_default desc", userId);
    }

    @Override
    public void insertAddress(ReceivingAddress R) throws SQLException {
        DBUtils.execute(conn,"insert  into s_receiving_address(id,receiving_address,receiving_person,mobile_phone,user_id,is_default) values (?,?,?,?,?,?)", R.getId(), R.getReceivingAddress(), R.getReceivingPerson(), R.getMobilePhone(), R.getUserId(), R.getIsDefault());
    }

    @Override
    public void updateAddress(ReceivingAddress R) throws SQLException {
        DBUtils.execute(conn,"update s_receiving_address set receiving_address=?,receiving_person=?,mobile_phone=?,user_id=?,is_default=? where id = ?",R.getReceivingAddress(), R.getReceivingPerson(), R.getMobilePhone(), R.getUserId(), R.getIsDefault(), R.getId());
    }

    @Override
    public void deleteAddress(String id) throws SQLException {
        DBUtils.execute(conn, "delete from s_receiving_address where id = ?", id);
    }

    @Override
    public void updateDefault(String id) throws SQLException {
        DBUtils.execute(conn, "update s_receiving_address set is_default = 1 where id = ?", id);
    }

    @Override
    public void deleteDefault() throws SQLException {
        DBUtils.execute(conn, "update s_receiving_address set is_default = -1");
    }
}
