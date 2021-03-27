package com.jiudian.manage.service.impl;


import com.github.pagehelper.PageHelper;
import com.jiudian.manage.mapper.LogMapper;
import com.jiudian.manage.model.Log;
import com.jiudian.manage.service.LogServiece;
import com.jiudian.manage.until.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogServiece {

    @Autowired
    LogMapper logMapper;

    @Override
    public boolean addLog(String householdname, int holdphone, String starttime, String endtime, double money, int roomid, int userid) {
        Log log = new Log();
        log.setHouseholdname(householdname);
        log.setHoldphone(holdphone);
        log.setStarttime(TimeUtil.formatterTime(starttime));
        log.setEndtime(TimeUtil.formatterTime(endtime));
        log.setRoomid(roomid);
        log.setUserid(userid);
        log.setMoney(money);

        int insert = logMapper.insertSelective(log);
        if(insert>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Log> getAllLog(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return logMapper.getAllUser();
    }

    @Override
    public int delLog() {
        if(logMapper.getAllUser()!=null) {
            logMapper.delete();
            return 1;
        }else return 0;
    }


}
