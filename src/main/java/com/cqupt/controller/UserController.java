package com.cqupt.controller;

import com.cqupt.dao.model.ComputerRoom;
import com.cqupt.dao.model.Page;
import com.cqupt.service.IUserService;
import com.cqupt.dao.model.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/userController")
public class UserController {
    @Resource
    IUserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login(User user,HttpServletResponse response, HttpServletRequest request, HttpSession session) throws IOException {
        User myuser=userService.login(user);
        Map<String, Object> result = new HashMap<>();
        if(myuser==null){
            result.put("success", false);
        }else {
            session.setAttribute("Id",myuser.getId());
            session.setAttribute("accountNum",myuser.getAccountNum());
            session.setAttribute("userName",myuser.getUserName());
            result.put("success", true);
            result.put("IsAdmin",myuser.getIsAdmin());
            result.put("userName",myuser.getUserName());
        }
        return result;
    }

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Map<String, Object> getUserInfo(HttpServletResponse response, HttpServletRequest request, HttpSession session) throws IOException {
        int total=userService.getTotal();
        Page page = new Page();
        page.setTotalCount(total);
        //String json=request.getParameter("data");
        //JSONObject jsonObject= JSONObject.fromObject(json);
        //String pageNum= jsonObject.getString("pageNum");
        page.setPageNo(1);
        Map<String, Object> map=new HashMap<>();
        map.put("start",page.getStartRow());
        map.put("size", page.getPageSize());
        List<User> users = userService.getUserInfo(map);
        JSONObject result=new JSONObject();
        JSONArray jsonArray=JSONArray.fromObject(users);
        result.put("totalPage", page.getTotalPage());
        result.put("data", jsonArray);
        result.put("userName",session.getAttribute("userName"));
        return result;
    }
}
