package com.jiudian.manage.service;

import com.jiudian.manage.model.Income;

import java.util.List;

public interface IncomeService {
    public boolean addIncome(double tmoney, double troom,String ttime);

    public List<Income> getAllLog(int pageNum, int pageSize);

    public Income getIncomeById();

}
