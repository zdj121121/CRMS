package com.cqupt.service;

import com.cqupt.dao.model.Device;

import java.util.List;
import java.util.Map;

public interface IDeviceService {
    List<Device> getPageData(Map<String,Object> map);

    int getTotal();

    int insertSelective(Device device);

        List<Device> selective(Map<String,Object> map);

    int getSelectiveNum(Map<String,Object> map);

    int deleteByPrimaryKey(int id);

    int updateByPrimaryKeySelective(Device device);
}
