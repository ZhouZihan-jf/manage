package com.jiudian.manage.mapper;

import com.jiudian.manage.model.Log;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogMapper {
    int insert(Log log);

    int insertSelective(Log log);

    List<Log> getAllUser();

    int delete();

    int deleteByPrimaryKey(Integer listid);

}
