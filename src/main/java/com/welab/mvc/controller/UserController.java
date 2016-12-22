package com.welab.mvc.controller;

import com.welab.mvc.model.User;
import com.welab.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoqian.wen
 * @create 2016-12-15 17:19
 **/
@RestController
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public Map<String, String> userList(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", "111");
        map.put("userName", "wenxq");
        return map;
    }

    @RequestMapping("/user/{id}")
    public User getUser(@PathVariable int id){
        return userService.getUser(id);
    }

}
