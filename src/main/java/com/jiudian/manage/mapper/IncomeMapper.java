package com.jiudian.manage.mapper;

import com.jiudian.manage.model.Income;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IncomeMapper {
    int insert(Income income);

    int insertSelective(Income income);

    List<Income> getAllUser();

    Income selectByPrimaryKey();

}
