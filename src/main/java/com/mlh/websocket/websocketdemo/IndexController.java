package com.mlh.websocket.websocketdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ltao
 * @Classname IndexController
 * @description: TODO
 * @date 2019/7/11
 * @Version 1.0
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public ModelAndView index(){
        return new ModelAndView("/index");
    }

    @RequestMapping("/toChat")
    public String toChat(){
        return "/chat";
    }
}
