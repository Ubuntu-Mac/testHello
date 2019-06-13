package com.qul.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by yanfazhongxin on 2019/6/12.
 */

@RestController
public class LoginController {

    @RequestMapping("/login")
    public String login(){
        return "134556";
    }
}
