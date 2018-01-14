package com.cqupt.controller;

import com.cqupt.dao.model.ComputerRoom;
import com.cqupt.dao.model.Device;
import com.cqupt.dao.model.Page;
import com.cqupt.service.IDeviceService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/deviceController")
public class DeviceController {
    @Resource
    IDeviceService deviceService;

    @RequestMapping("/getPageDate")
    @ResponseBody
    public Map<String,Object> getPageDate(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                          HttpSession session){
        int total=deviceService.getTotal();
        Page page = new Page();
        page.setTotalCount(total);
        page.setPageNo(pageNum);
        Map<String, Object> map=new HashMap<>();
        map.put("start",page.getStartRow());
        map.put("size", page.getPageSize());
        List<Device> devices = deviceService.getPageData(map);
        JSONObject result=new JSONObject();
        JSONArray jsonArray=JSONArray.fromObject(devices);
        result.put("totalPage", page.getTotalPage());
        result.put("data", jsonArray);
        result.put("userName",session.getAttribute("userName"));
        return result;
    }

    @RequestMapping("/selective")
    @ResponseBody
    public Map<String,Object> selective(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "key") String key,
                                        @RequestParam(value = "value") String value,
                                        HttpSession session){
        //key="lab_name";
        //value= String.valueOf(2);
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("keyWord",key);
        map.put("keyValue",value);
        int total = deviceService.getSelectiveNum(map);
        JSONObject result = new JSONObject();
        if(total!=0&&total>0) {
            Page page = new Page();
            page.setTotalCount(total);
            map.put("start",page.getStartRow());
            map.put("size", page.getPageSize());
            List<Device> devices = deviceService.selective(map);
            JSONArray jsonArray = JSONArray.fromObject(devices);
            result.put("totalPage", page.getTotalPage());
            result.put("data", jsonArray);
            result.put("userName",session.getAttribute("userName"));
        }else {

        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Boolean> delete(@RequestParam(value = "id") String id, HttpServletResponse response, HttpServletRequest request)throws IOException {

        int num = deviceService.deleteByPrimaryKey(Integer.parseInt(id));
        Map<String, Boolean> result = new HashMap<>();
        if(num==0){
            result.put("success", false);
        }else {
            result.put("success", true);
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Boolean> update( Device device, HttpServletResponse response, HttpServletRequest request)throws IOException{
        int num = deviceService.updateByPrimaryKeySelective(device);
        Map<String, Boolean> result = new HashMap<>();
        if(num==0){
            result.put("success", false);
        }else {
            result.put("success", true);
        }
        return result;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Map<String, Boolean> add( Device device, HttpServletResponse response, HttpServletRequest request)throws IOException{
        int num = deviceService.insertSelective(device);
        Map<String, Boolean> result = new HashMap<>();
        if(num==0){
            result.put("success", false);
        }else {
            result.put("success", true);
        }
        return result;
    }
}
