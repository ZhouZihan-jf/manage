package com.jiudian.manage.mapper;

import com.jiudian.manage.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> getAllUser();

    List<User> selectByPower(Integer power);

    User selectByAccount(String useraccount);
}