package com.mlh.aop;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ltao
 * @Classname AopTest
 * @description: TODO
 * @date 2019/7/12
 * @Version 1.0
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AopTest {

    @Autowired
    AopService aopService;
    @Test
    public void testAop(){
        String rtv = aopService.doSomething("哈哈哈");
        log.info(rtv);
    }
}
