package com.cqupt.service.impl;

import com.cqupt.dao.mapper.DeviceMapper;
import com.cqupt.dao.model.Device;
import com.cqupt.service.IDeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class DeviceServiceImpl implements IDeviceService{
    @Resource
    DeviceMapper deviceMapper;

    public List<Device> getPageData(Map<String, Object> map) {
        return deviceMapper.getPageDate(map);
    }

    public int getTotal() {
        return deviceMapper.getTotal();
    }

    public int insertSelective(Device device){return deviceMapper.insertSelective(device);}

    public List<Device> selective(Map<String,Object> map){
        return deviceMapper.selective(map);
    }

    public int getSelectiveNum(Map<String,Object> map){return deviceMapper.getSelectiveNum(map);}

    public int deleteByPrimaryKey(int id){return deviceMapper.deleteByPrimaryKey(id);}

    public int updateByPrimaryKeySelective(Device device){return deviceMapper.updateByPrimaryKeySelective(device);}
}
