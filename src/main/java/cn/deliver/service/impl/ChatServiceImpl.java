package cn.deliver.service.impl;

import cn.deliver.dao.ChatDao;
import cn.deliver.domain.Message;
import cn.deliver.domain.Result;
import cn.deliver.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ming
 * @date 2019/10/16 - 22:59
 */
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatDao chatDao;

    /**
     * 保存聊天记录
     * @param message 保存的信息
     * @return 返回更改条数
     */
    @Override
    public int insertMessage(Message message) {
        int id = chatDao.insertMessage(message);
        return id;
    }

    /**
     * 根据用户id查找所有的聊天记录
     * @param to_id
     * @param status
     * @return
     */
    @Override
    public List<Message> findUserAllMessageByTS(int to_id,int status) {
        List<Message> messageList = chatDao.findUserAllMessageByTS(to_id,status);
        return messageList;
    }

    /**
     * 更改用户未读的信息状态
     * @param from_id
     * @param to_id
     * @return
     */
    @Override
    public Result changeUnreadMessage(int from_id, int to_id) {
        List<Message> messageList = chatDao.findUserAllMessageByFTS(from_id,to_id);
        if(messageList.size()==0){
            return new Result("两位用户未有未读信息","0",null);
        }else {
            for (Message message:messageList){
                System.out.println(message);
                chatDao.changeMessageStatus(message.getId(),1);
            }
            return new Result("更改成功","0",null);
        }
    }

    /**
     * 查找两个用户之间所有聊天记录
     * @param from_id
     * @param to_id
     * @return
     */
    @Override
    public List<Message> findUserAllMessageByTF(int to_id, int from_id) {
        List<Message> messageList = chatDao.findUserAllMessageByTF(to_id,from_id);
        return messageList;
    }
}
