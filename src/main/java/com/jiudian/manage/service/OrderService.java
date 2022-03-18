package com.jiudian.manage.service;

import com.jiudian.manage.model.Order;

import java.util.List;



public interface OrderService {
    /**
     * 增加订单
     * @param householdname
     * @param id
     * @param starttime
     * @param endtime
     * @param roomid
     * @param userid
     * @return
     */
    public boolean addOrder(String householdname, String id,long holdphone, String starttime, String endtime, int roomid, int userid);

    /**
     * 删除订单
     * @param orderid
     * @return
     */
    public boolean delOrder(int orderid);

    /**
     * 修改订单状态
     * @param orderid
     * @param state
     * @return
     */
    public boolean updateOrderState(int orderid,int state);

    /**
     *获取所有订单
     * @return
     */
    public List<Order> getAllOrder(int pageNum, int pageSize);

    /**
     *获取所有订单
     * @param holdphone
     * @return
     */

    public List<Order> getListByPhone(int pageNum,int pageSize,long holdphone);

    public List<Order> getListByUser(int pageNum,int pageSize,int userid);

}
