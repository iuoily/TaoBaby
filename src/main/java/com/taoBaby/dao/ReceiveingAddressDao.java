package com.taoBaby.dao;

import com.taoBaby.pojo.ReceivingAddress;

import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoily on 2022/5/21.
 */
public interface ReceiveingAddressDao {

    /**
     * 获取所有收货地址
     * @return 收货地址列表
     */
    List<ReceivingAddress> getAddressList(String userId) throws Exception;

    /**
     * 新增收货地址
     * @param receivingAddress 收货地址
     */
    void insertAddress(ReceivingAddress receivingAddress) throws SQLException;

    /**
     * 修改收货地址
     * @param receivingAddress 收货地址
     */
    void updateAddress(ReceivingAddress receivingAddress) throws SQLException;

    /**
     * 删除地址
     */
    void deleteAddress(String id) throws SQLException;

    /**
     * 修改默认地址
     * @param id 默认id
     */
    void updateDefault(String id) throws SQLException;

    /**
     * 删除默认地址
     */
    void deleteDefault() throws SQLException;
}
