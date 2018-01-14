package com.cqupt.service;

import com.cqupt.dao.model.ComputerRoom;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface IComputerRoomService {

   List<ComputerRoom> getPageData(Map<String,Object>map);

   int getTotal();

   int insertSelective(ComputerRoom computerRoom);

   List<ComputerRoom> selective(Map<String,Object> map);

   int getSelectiveNum(Map<String,Object> map);

   int deleteByPrimaryKey(int id);

   int updateByPrimaryKeySelective(ComputerRoom computerRoom);
}
