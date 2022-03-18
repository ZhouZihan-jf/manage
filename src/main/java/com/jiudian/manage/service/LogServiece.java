package com.jiudian.manage.service;

import com.jiudian.manage.model.Log;

import java.util.List;

public interface LogServiece {
    /**
     * 增加日志
     * @param householdname
     * @param holdphone
     * @param starttime
     * @param endtime
     * @param money
     * @param roomid
     * @param userid
     * @return
     */
    public boolean addLog(String householdname, long holdphone, String starttime, String endtime, double money,int roomid, int userid);

    //查询订单列表
    public List<Log> getAllLog(int pageNum, int pageSize);

    /**
     * 删除日志
     * @return
     */
    public int delLog();


}
