package cn.deliver.controller;

import cn.deliver.domain.Message;
import cn.deliver.domain.Result;
import cn.deliver.domain.User;
import cn.deliver.service.ChatService;
import cn.deliver.service.UserService;
import cn.deliver.websocket.ChatWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author Ming
 * @date 2019/10/16 - 22:55
 */
@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    ChatService chatService;

    @Autowired
    UserService userService;

    //跳转登录页面
    @RequestMapping(value = "/loginpage" , method = RequestMethod.GET)
    public ModelAndView loginpage(HttpServletRequest request){

        System.out.println("跳转到loginpage");
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        return new ModelAndView("login");
    }



    //登录进入聊天主页
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody Map<String,Object> map , HttpServletRequest request){
        //
        HttpSession session = request.getSession();
        //登录操作，判断是否是一个已经登录的用户，没有则登录
        if(null!=session.getAttribute("user")){
            //清除久的用户
            session.removeAttribute("user");
        }
        //新登录
        User user = userService.findUserById(Integer.parseInt((String)map.get("id")));
        session.setAttribute("user",user);
        System.out.println("=========================================================="+session.getId());

        if(null == user){
            System.out.println("================================"+user);
            return new Result("跳转的页面","1","login.html");
        }else {
            System.out.println("+++++++++++++++++++++++++++++++++"+user);
            return new Result("跳转的页面","0","main.html");
        }

    }

    /**
     * 根据状态查找用户聊天记录
     * @param map
     * @return
     */
    @RequestMapping(value = "findUserAllMessageByTS",method = RequestMethod.POST)
    @ResponseBody
    public Result findUserAllMessageByTS(@RequestBody Map<String,Object> map,HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+session.getId());
        List<Message> messageList = chatService.findUserAllMessageByTS((int)map.get("to_id"),(int)map.get("status"));
        return new Result("查找结果","0",messageList);
    }

    /**
     * 更改用户与某用户未读信息为已读
     * @param map
     * @return
     */
    @PostMapping("changeUnreadMessage")
    @ResponseBody
    public Result changeUnreadMessage(@RequestBody Map<String,Object> map){
        Result result = chatService.changeUnreadMessage((int)map.get("from_id"),(int)map.get("to_id"));
        return result;
    }

    /**
     * 查找两个用户之间所有聊天记录
     * @param map
     * @return
     */
    @PostMapping("findUserAllMessageByTF")
    @ResponseBody
    public Result findUserAllMessageByTF(@RequestBody Map<String,Object> map){
        List<Message> messageList = chatService.findUserAllMessageByTF((int)map.get("to_id"),(int)map.get("from_id"));
        return new Result("两用户之间所有聊天记录","0",messageList);
    }


    /**
     * 判断用户是否在线
     * @param map 请求体
     * @return 返回
     */
    @RequestMapping(value = "judgeOnLine",method = RequestMethod.POST)
    @ResponseBody
    public Result judgeOnLine(@RequestBody Map<String,Object> map){
        return ChatWebSocketHandler.judgeOnLine((String) map.get("id"));
    }

}
