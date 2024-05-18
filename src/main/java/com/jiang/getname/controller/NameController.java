package com.jiang.getname.controller;

import com.jiang.yaziapiclientsdk.model.User;
import com.jiang.yaziapiclientsdk.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/***
 *服务端
 */
@RestController
@RequestMapping("/name")
public class NameController {

    private String accessServeKey="jiangyanming";
    private String secretKey="123456";

    @GetMapping("/get")
    public String getNameByGet(String name){
        return "get的方式得到name=>"+ name;
    }

    @PostMapping("/post")
    public String getNameByPost(@RequestParam String name){
        return "post的方式得到name=>"+ name;
    }


    @PostMapping("/userGetName")
    public String getNameByUser(@RequestBody User user, HttpServletRequest request){ //以Json的格式提交

        return "user的方式得到name=>"+ user.getUserName();
    }

}
