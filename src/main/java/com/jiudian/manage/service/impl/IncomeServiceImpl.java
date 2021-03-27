package com.jiudian.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.jiudian.manage.mapper.IncomeMapper;
import com.jiudian.manage.model.Income;
import com.jiudian.manage.service.IncomeService;
import com.jiudian.manage.until.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    IncomeMapper incomeMapper;

    @Override
    public boolean addIncome(double tmoney, double troom, String ttime) {
        Income income = new Income();

        income.setTtime(TimeUtil.formatterTime(ttime));
        income.setTmoney(tmoney);
        income.setTroom(troom);

        int insert = incomeMapper.insertSelective(income);
        if(insert>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Income> getAllLog(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return incomeMapper.getAllUser();
    }

    @Override
    public Income getIncomeById() {
        return incomeMapper.selectByPrimaryKey();
    }
}
