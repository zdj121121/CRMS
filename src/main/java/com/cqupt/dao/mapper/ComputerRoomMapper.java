package com.cqupt.dao.mapper;

import com.cqupt.dao.model.ComputerRoom;

public interface ComputerRoomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ComputerRoom record);

    int insertSelective(ComputerRoom record);

    ComputerRoom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComputerRoom record);

    int updateByPrimaryKey(ComputerRoom record);
}