package com.taobaby.service;

import com.taobaby.pojo.ReceivingAddress;

import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoily on 2022/5/21.
 */
public interface ReceiveingAddressService {

    /**
     * 获取所有收货地址
     * @return 收货地址列表
     */
    List<ReceivingAddress> listAddress(String userId) throws Exception;

    /**
     * 新增收货地址
     * @param receivingAddress 收货地址
     */
    void addAddress(ReceivingAddress receivingAddress) throws SQLException;

    /**
     * 删除地址
     */
    void removeAddress(String id) throws SQLException;

    /**
     * 修改收货地址
     * @param receivingAddress 收货地址
     */
    void modifyAddress(ReceivingAddress receivingAddress) throws SQLException;

    /**
     * 修改默认地址
     * @param id 默认id
     */
    void modifyDefault(String id) throws SQLException;
}
