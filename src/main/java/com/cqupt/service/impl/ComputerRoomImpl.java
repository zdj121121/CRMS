package com.cqupt.service.impl;

import com.cqupt.dao.mapper.ComputerRoomMapper;
import com.cqupt.dao.model.ComputerRoom;
import com.cqupt.service.IComputerRoomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

@Service
public class ComputerRoomImpl implements IComputerRoomService{
    @Resource
    ComputerRoomMapper computerRoomMapper;


    public List<ComputerRoom> getPageData(Map<String, Object> map) {
        return computerRoomMapper.getPageDate(map);
    }

    public int getTotal() {
        return computerRoomMapper.getTotal();
    }

    public int insertSelective(ComputerRoom computerRoom){return computerRoomMapper.insertSelective(computerRoom);}

    public List<ComputerRoom> selective(Map<String,Object> map){
        return computerRoomMapper.selective(map);
    }

    public int getSelectiveNum(Map<String,Object> map){return computerRoomMapper.getSelectiveNum(map);}

    public int deleteByPrimaryKey(int id){return computerRoomMapper.deleteByPrimaryKey(id);}

    public int updateByPrimaryKeySelective(ComputerRoom computerRoom){return computerRoomMapper.updateByPrimaryKeySelective(computerRoom);}
}
