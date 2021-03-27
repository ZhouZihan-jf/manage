package com.jiudian.manage.mapper;

import com.jiudian.manage.model.Room;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomMapper {
    int deleteByPrimaryKey(Integer roomid);

    int insert(Room record);

    int insertSelective(Room record);

    Room selectByPrimaryKey(Integer roomid);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);

    List<Room> selectRoomByStateType(Room stateType);


}