package com.mlh.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ltao
 * @Classname AopService
 * @description: TODO
 * @date 2019/7/12
 * @Version 1.0
 */
@Component
@Slf4j
public class AopService {

    public String doSomething(String args){
        log.info("AopService.doSomething(String):"+args);
        return "My return";
    }
}
