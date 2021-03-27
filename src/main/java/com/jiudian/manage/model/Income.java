package com.jiudian.manage.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Api(value = "收入bean")
public class Income {
    private Integer inid;
    private double tmoney;
    private double troom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd", timezone = "GMT+8")
    private Date ttime;

    public Date getTtime() {
        return ttime;
    }

    public void setTtime(Date ttime) {
        this.ttime = ttime;
    }

    public Integer getInid() {
        return inid;
    }

    public void setInid(Integer inid) {
        this.inid = inid;
    }

    public double getTmoney() {
        return tmoney;
    }

    public void setTmoney(double tmoney) {
        this.tmoney = tmoney;
    }

    public double getTroom() {
        return troom;
    }

    public void setTroom(double troom) {
        this.troom = troom;
    }
}
