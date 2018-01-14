package com.cqupt.dao.mapper;

import com.cqupt.dao.model.ComputerRoom;


import java.util.List;
import java.util.Map;

public interface ComputerRoomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ComputerRoom record);

    int insertSelective(ComputerRoom record);

    ComputerRoom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComputerRoom record);

    int updateByPrimaryKey(ComputerRoom record);

    List<ComputerRoom> getPageDate(Map<String, Object> map);

    int getTotal();

    List<ComputerRoom> selective(Map<String,Object> map);

    int getSelectiveNum(Map<String,Object> map);

}