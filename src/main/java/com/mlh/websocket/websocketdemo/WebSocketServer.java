package com.mlh.websocket.websocketdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ltao
 * @Classname WebSocketServer
 * @description: TODO
 * @date 2019/7/11
 * @Version 1.0
 */
@Slf4j
@ServerEndpoint("/websocket/{userId}")
@Component
public class WebSocketServer  {
    //
    private static int onlineCount = 0;

    private static Map<String,WebSocketServer> users = Collections.synchronizedMap(new HashMap<>());

    private Session session;

    private String username;


    @OnOpen
    public void onOpen(Session session,@PathParam("userId") String username){
        this.session = session;
        this.username = username;
        users.put(username,this);
        addOnlineCount();
        log.info(username+"加入！当前在线人数为" + getOnlineCount());
        try {
            this.session.getBasicRemote().sendText("连接成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(){
        users.remove(this.username);
        subOnlineCount();
        log.info("一个连接关闭！当前在线人数为：" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message){
        log.info("来自客户端的消息:" + message);

        try {
            if(StringUtils.isEmpty(message)){
                return;
            }
            //如果给所有人发消息携带@ALL, 给特定人发消息携带@xxx@xxx#message
            String[] split = message.split("#");
            if (split.length>1){
                String[] users = split[0].split("@");
                if (users.length<2){return;}
                String firstuser = users[1].trim();
                if (StringUtils.isEmpty(firstuser)||"ALL".equals(firstuser.toUpperCase())){
                    String msg =username +": "+ split[1];
                    sendInfo(msg);//群发消息
                }else{//给特定人员发消息
                    for (String user : users) {
                        if (!StringUtils.isEmpty(user.trim())){
                            sendMessageToSomeBody(user.trim(),split[1]);
                        }
                    }
                }
            }else{
                sendInfo(username +": "+message);
            }
        } catch (IOException e){

        }
    }
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误 session: "+session);
        error.printStackTrace();
    }

    //    给特定人员发送消息
    public void sendMessageToSomeBody(String username,String message) throws IOException {
        if(users.get(username)==null){
            return;
        }
        users.get(username).session.getBasicRemote().sendText(message);
        this.session.getBasicRemote().sendText(this.username+"@"+username+": "+message);
    }

    /**
     * 群发自定义消息
     */
    public  void sendInfo(String message) throws IOException {
        for (WebSocketServer item : users.values()) {
            try {
                item.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                continue;
            }
        }
    }


    private synchronized static void subOnlineCount() {
        onlineCount-- ;
    }

    private synchronized static int getOnlineCount() {
        return onlineCount ;
    }

    private synchronized static void addOnlineCount() {
        onlineCount++ ;
    }

}
