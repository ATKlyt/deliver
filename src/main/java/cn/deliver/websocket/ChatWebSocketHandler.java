package cn.deliver.websocket;

import cn.deliver.domain.Message;
import cn.deliver.domain.Result;
import cn.deliver.domain.User;
import cn.deliver.service.ChatService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Ming
 * @date 2019/8/5 - 16:58
 */
public class ChatWebSocketHandler implements WebSocketHandler {

    @Autowired
    private ChatService chatService;

    private static final Logger log = Logger.getLogger(ChatWebSocketHandler.class);

    //在线用户的SocketSession (存储所有的通信通道)
    public static final Map<String , WebSocketSession> USER_SOCKETSESSION_MAP;

    //存储所有的在线用户
    static{
        USER_SOCKETSESSION_MAP = new HashMap<String, WebSocketSession>();
    }


    /**
     * webSocket 建立好链接之后的处理函数 --> 连接建立后的准备工作
     * */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        Gson gson = new Gson();
        //将当前的连接的用户会话放入MAP ，key是用户编号
        User user = (User) webSocketSession.getAttributes().get("user");

        USER_SOCKETSESSION_MAP.put(user.getId()+"",webSocketSession);
        System.out.println("用户Id"+ user.getId() +"==============================有连接了");

        //群发消息告知大家
        Message msg = new Message();
        msg.setMessage("风骚的【" + user.getAuthId()+ "】踩着轻盈的步伐来啦。。。大家欢迎！");
        msg.setDate(new Timestamp(System.currentTimeMillis()));
        //获取所有在线的WebSocketSession对象集合
        Set<Map.Entry<String, WebSocketSession>> entrySet = USER_SOCKETSESSION_MAP.entrySet();

//        for(Map.Entry<String, WebSocketSession> entry:entrySet){
//            msg.getUserList().add((CharUser) entry.getValue().getAttributes().get("loginUser"));
//        }

        //将消息转换为json
        //TextMessage message = new TextMessage(GsonUtiles.toJson(msg));
        TextMessage message = new TextMessage(gson.toJson(msg));
        //群发消息
        sendMessageToAll(message);

    }

    /**
     * 客户端发送服务器的消息时的处理函数
     * 处理信息：当一个新的消WebSocket到达的时候，会调用（客户端通过WebSocket API 发送的消息会结果这个地方，然后进行相应的处理）
     * */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

        Gson gson = new Gson();

        //如果消息没有如何内容，则返回
        if(message.getPayloadLength() == 0){
            return;
        }
        // 将消息JSON格式通过Gson转换成Map
        // message.getPayload().toString() 获取消息具体内容
        Map<String, Object> msg = gson.fromJson(message.getPayload().toString(),
                new TypeToken<Map<String, Object>>() {}.getType());
        log.info("handleMessage......."+message.getPayload()+"..........."+msg);
        session.sendMessage(message);

        Message myMessage = new Message();
        myMessage.setDate(new Timestamp(System.currentTimeMillis()));
        myMessage.setFrom(Integer.parseInt(msg.get("from").toString()));
        myMessage.setMessage(msg.get("msgContent").toString());
//        myMessage.setTo(null);
        myMessage.setTo(Integer.parseInt((String) msg.get("to")));
        System.out.println(myMessage);


        if(null == msg.get("to")|| msg.get("to").toString().equals("-1")){
            System.out.println("群发给用户！");
            //群发
            //sendMessageToAll(new TextMessage(GsonUtiles.toJson(myMessage)));
            sendMessageToAll(new TextMessage(gson.toJson(myMessage)));
        }else{
            System.out.println("单发给" + msg.get("to").toString());
            //单发
            //sendMessageToUser(msg.get("to").toString(),new TextMessage(GsonUtiles.toJson(myMessage)));
            sendMessageToUser(msg.get("to")+"",myMessage);
        }
    }

    /**
     * 消息传输过程出现错误的异常处理函数
     * 处理传输错误：处理由底层WebSocket 消息传输过程中发生异常
     * */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        //记录日志，关闭连接
        System.out.println("WebSocket异常断开：" + session.getId() + "已经关闭");
        //一旦发生异常，强制用户下线，关闭session
        if (session.isOpen()) {
            session.close();
        }
        //群发通知大家
        Message msg = new Message();
        msg.setDate(new Timestamp(System.currentTimeMillis()));

        //获取异常的用户的会话中的编号
        //CharUser charUser = (CharUser) session.getAttributes().get("loginUser");
        User user = (User) session.getAttributes().get("user");
        //获取所有的用户的会话
        Set<Map.Entry<String, WebSocketSession>> entrySet = USER_SOCKETSESSION_MAP.entrySet();
        //找出在线的用户发送消息并移除用户
        for (Map.Entry<String, WebSocketSession> entry : entrySet) {
            if (entry.getKey().equals(user.getId())) {
                msg.setMessage("万众瞩目的【" + user.getAuthId() + "】已经退出。。。！");
                //清除在线会话
                USER_SOCKETSESSION_MAP.remove(entry.getKey());
                //记录日志
                System.out.println("Socket会话已经移除：用户ID" + entry.getKey());
                break;
            }
        }
    }

    /**
     * webSocket断开后的回调
     * 连接关闭后：一般是回收资源等
     * */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        //记录日志
        System.out.println("WebSocket正常断开："+session.getId() + "已经关闭");

        Gson gson = new Gson();

        //群发告诉大家
        Message msg = new Message();
        msg.setDate(new Timestamp(System.currentTimeMillis()));

        //获取异常的用户编号
        User user = (User) session.getAttributes().get("user");
        //获取所有的用户的会话
        Set<Map.Entry<String, WebSocketSession>> entrySet = USER_SOCKETSESSION_MAP.entrySet();
        for (Map.Entry<String, WebSocketSession> entry : entrySet) {
            if (Integer.parseInt(entry.getKey()) == user.getId()) {
                msg.setMessage("万众瞩目的【" + user.getAuthId() + "】已经有事先走了，大家继续聊。。。！");
                //清除在线会话
                USER_SOCKETSESSION_MAP.remove(entry.getKey());
                //记录日志
                System.out.println("Socket会话已经移除：用户ID" + entry.getKey());
                //TextMessage textMessage = new TextMessage(GsonUtiles.toJson(msg));
                TextMessage textMessage = new TextMessage(gson.toJson(msg));
                sendMessageToAll(textMessage);
                break;
            }
        }
    }

    /**
     *  是否支持处理拆分消息，返回true 返回拆分消息
     * @return 重写返回 boolean 型
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


    /**
     * 根据id私发消息
     * @param id session对应的 id
     * @param message 发送的信息
     * @throws IOException 异常
     */
    private void sendMessageToUser(String id , Message message) throws IOException {
        WebSocketSession webSocketSession = USER_SOCKETSESSION_MAP.get(id);
        Gson gson = new Gson();
        TextMessage textMessage = null;
        if(webSocketSession != null && webSocketSession.isOpen()){
            //用户在线
            message.setType(1);
            message.setStatus(1);
            textMessage = new TextMessage(gson.toJson(message));
            chatService.insertMessage(message);
            webSocketSession.sendMessage(textMessage);
        }else {
            //用户不在线
            message.setType(1);
            message.setStatus(0);
            textMessage = new TextMessage(gson.toJson(message));
            chatService.insertMessage(message);
//            webSocketSession.sendMessage(textMessage);
        }
    }


    /**
     * 群发消息：给所有在线的用户
     * */
    private void sendMessageToAll(final TextMessage message){
        //对所有用户发送的信息进行转义

        //获取到所有的在线用户的SocketSession 对象
        Set<Map.Entry<String, WebSocketSession>> entrySet = USER_SOCKETSESSION_MAP.entrySet();
        for (Map.Entry<String, WebSocketSession> entry : entrySet){
            //某用户的WebSocketSession
            final WebSocketSession webSocketSession = entry.getValue();
            //判断连接是否仍然存在
            if (webSocketSession.isOpen()){
                //开启多线程发送消息（效率高）
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(webSocketSession.isOpen()){
                            try {
                                webSocketSession.sendMessage(message);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        }
    }


    /**
     * 判断用户是否登录
     * @param id 用户的id值
     * @return 返回整个result对象
     */
    public static Result judgeOnLine(String id){
        WebSocketSession webSocketSession = USER_SOCKETSESSION_MAP.get(id);
        if (null == webSocketSession || !webSocketSession.isOpen()){
            return new Result("用户不在线","1",null);
        }else{
            return new Result("用户在线","0",null);
        }
    }

}
