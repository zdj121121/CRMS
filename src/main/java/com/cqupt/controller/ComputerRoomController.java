package com.cqupt.controller;

import com.cqupt.dao.model.ComputerRoom;
import com.cqupt.dao.model.Page;
import com.cqupt.service.IComputerRoomService;
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
@RequestMapping("/computerRoomController")
public class ComputerRoomController {
    @Resource
    IComputerRoomService computerRoomService;

    @RequestMapping("/getPageData")
    @ResponseBody
    public Map<String,Object> getPageDate(HttpServletRequest request,HttpSession session){
        int total=computerRoomService.getTotal();
        Page page = new Page();
        page.setTotalCount(total);
         String json=request.getParameter("data");
         JSONObject jsonObject= JSONObject.fromObject(json);
        //String pageNum= jsonObject.getString("pageNum");
        page.setPageNo(1);
        Map<String, Object> map=new HashMap<>();
        map.put("start",page.getStartRow());
        map.put("size", page.getPageSize());
        List<ComputerRoom> computerRoom = computerRoomService.getPageData(map);
        JSONObject result=new JSONObject();
        JSONArray jsonArray=JSONArray.fromObject(computerRoom);
        result.put("totalPage", page.getTotalPage());
        result.put("data", jsonArray);
        result.put("userName",session.getAttribute("userName"));
        return result;
    }

    @RequestMapping("/selective")
    @ResponseBody
    public Map<String,Object> selective(HttpServletRequest request, HttpSession session){
        String json=request.getParameter("data");
        JSONObject jsonObject= JSONObject.fromObject(json);
         //key="lab_name";
        //value= String.valueOf(2);
        String key = jsonObject.getString("key");
        String value= jsonObject.getString("value");
        String searchText = new StringBuilder("'%").append(value).append("%'").toString();
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("keyWord",key);
        map.put("keyValue",searchText);
        int total = computerRoomService.getSelectiveNum(map);
        JSONObject result = new JSONObject();
        if(total!=0&&total>0) {
            Page page = new Page();
            page.setTotalCount(total);
            map.put("start",page.getStartRow());
            map.put("size", page.getPageSize());
            List<ComputerRoom> computerRooms = computerRoomService.selective(map);
            JSONArray jsonArray = JSONArray.fromObject(computerRooms);
            result.put("totalPage", page.getTotalPage());
            result.put("data", jsonArray);
            result.put("userName",session.getAttribute("userName"));
        }else {

        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Boolean> delete(@RequestParam(value = "id") String id,HttpServletResponse response, HttpServletRequest request)throws IOException {

        int num = computerRoomService.deleteByPrimaryKey(Integer.parseInt(id));
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
    public Map<String, Boolean> update( ComputerRoom computerRoom, HttpServletResponse response, HttpServletRequest request)throws IOException{
        int num = computerRoomService.updateByPrimaryKeySelective(computerRoom);
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
    public Map<String, Boolean> add( ComputerRoom computerRoom, HttpServletResponse response, HttpServletRequest request)throws IOException{
        int num = computerRoomService.insertSelective(computerRoom);
        Map<String, Boolean> result = new HashMap<>();
        if(num==0){
            result.put("success", false);
        }else {
            result.put("success", true);
        }
        return result;
    }
}
