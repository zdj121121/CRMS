package com.cqupt.controller;

import com.cqupt.service.IUserService;
import com.cqupt.service.dao.model.User;
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
import java.util.Map;

@Controller
@RequestMapping("/userController")
public class UserController {
    @Resource
    IUserService userService;

    @RequestMapping("/login")
    public Map<String, Boolean> login(HttpServletResponse response, HttpServletRequest request, HttpSession session) throws IOException {
       // String json=request.getParameter("user");
        //JSONObject jsonObject= JSONObject.fromObject(json);
        //String user_phone=jsonObject.getString("user_phone");
        //String user_pwd=jsonObject.getString("user_pwd");
       // User user =new User();
       // user.setId(1);
        User myuser=userService.login(1);
        Map<String, Boolean> result = new HashMap<>();
        if(myuser==null){
            result.put("success", false);
        }else {
            session.setAttribute("myuser",myuser);
            result.put("success", true);
        }
        
        return result;
    }
}
