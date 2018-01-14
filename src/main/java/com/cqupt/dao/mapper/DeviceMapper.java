package com.cqupt.dao.mapper;

import com.cqupt.dao.model.Device;

import java.util.List;
import java.util.Map;

public interface DeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

    List<Device> getPageDate(Map<String, Object> map);

    int getTotal();

    List<Device> selective(Map<String,Object> map);

    int getSelectiveNum(Map<String,Object> map);
}