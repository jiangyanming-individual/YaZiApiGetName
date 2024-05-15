package com.jiang.springname.controller;

import com.jiang.springname.model.User;
import com.jiang.springname.utils.SignUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

/***
 *服务端
 */
@RestController
@RequestMapping("/name")
public class NameController {

    private String accessServeKey="jiangyanming";
    private String secretKey="123456";

    @GetMapping("/")
    public String getNameByGet(String name){
        return "get的方式得到name=>"+ name;
    }

    @PostMapping("/")
    public String getNameByPost(String name){
        return "post的方式得到name=>"+ name;
    }


    @PostMapping("/userGetName")
    public String getNameByUser(User user, HttpServletRequest request){

        String accessKey = request.getHeader("accessKey");
        String body = request.getHeader("body");
        String sign = request.getHeader("sign");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");

        //校验随机数字段
        if (Long.parseLong(nonce) > 10000){
            throw new RuntimeException("无权限");
        }
        //校验时间字段 ,防止重放攻击，限制五分钟以内
        if ( System.currentTimeMillis() / 1000 >  (Long.parseLong(timestamp) + 300)){
            throw new RuntimeException("无权限");
        }
        //校验accessKey
        if (!accessKey.equals(accessServeKey)){
            throw new RuntimeException("无权限");
        }
        //实际是从数据库中查出
        String genSign = SignUtils.getSign(body, secretKey);
        if (!genSign.equals(sign)){
            throw new RuntimeException("无权限");
        }
        return "user的方式得到name=>"+ user.getUserName();
    }

}