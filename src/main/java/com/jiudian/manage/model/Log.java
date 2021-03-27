package com.jiudian.manage.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Api(value = "日志bean")
public class Log {
    private Integer listid;
    private String householdname;
    private Integer holdphone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd", timezone = "GMT+8")
    private Date starttime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd", timezone = "GMT+8")
    private Date endtime;
    private double money;
    private Integer roomid;
    private Integer userid;

    public Integer getListid() {
        return listid;
    }

    public void setListid(Integer listid) {
        this.listid = listid;
    }

    public String getHouseholdname() {
        return householdname;
    }

    public void setHouseholdname(String householdname) {
        this.householdname = householdname;
    }

    public Integer getHoldphone() {
        return holdphone;
    }

    public void setHoldphone(Integer holdphone) {
        this.holdphone = holdphone;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
