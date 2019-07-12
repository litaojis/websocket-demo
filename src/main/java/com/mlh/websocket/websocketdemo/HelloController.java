package com.mlh.websocket.websocketdemo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ltao
 * @Classname HelloController
 * @description: TODO
 * @date 2019/7/11
 * @Version 1.0
 */
@RestController
@Slf4j
public class HelloController {


    @RequestMapping("/hello")
    public String hello(){
        return "hello";

    }
}
