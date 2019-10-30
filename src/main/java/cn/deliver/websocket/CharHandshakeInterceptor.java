package cn.deliver.websocket;


import cn.deliver.domain.User;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Ming
 * @date 2019/8/5 - 16:40
 */
public class CharHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    /**
     * 握手之前，若返回false，则不建立链接
     * @param attributes  webSocket的session域
     * */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if(request instanceof ServerHttpRequest){
            ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
            HttpSession session = serverHttpRequest.getServletRequest().getSession(false);
            System.out.println("---------------------------------------------"+session.getId());
            //如果用户已经登录，也许聊天
            if(session.getAttribute("user")!=null){
                //获取登录的用户
                User user = (User) session.getAttribute("user");
                //将用户放入socket处理器的话（WebSocketSession）中
                attributes.put("user",user);
                System.out.println("WebSocket：用户[ID：" + user.getId() + "，Name：" + user.getAuthId() + "]要建立连接!");
            }else {
                //用户没有登录，拒绝聊天
                //握手失败、
                System.out.println("---------------------握手失败...");
                return false;
            }
        }
        System.out.println("---------------------握手开始...");
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }


    /**
     * 握手之后
     * */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        System.out.println("---------------------握手成功...");
        super.afterHandshake(request, response, wsHandler, exception);

    }
}
