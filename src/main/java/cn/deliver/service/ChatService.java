package cn.deliver.service;

import cn.deliver.domain.Message;
import cn.deliver.domain.Result;

import java.util.List;

/**
 * @author Ming
 * @date 2019/10/16 - 22:56
 */
public interface ChatService {

    /**
     * 插入信息
     * @param message
     * @return
     */
    int insertMessage(Message message);

    /**
     * 根据用户id查找所有的聊天记录
     * @param to_id
     * @param status
     * @return
     */
    List<Message> findUserAllMessageByTS(int to_id,int status);

    /**
     * 更改用户未读信息的状态
     * @param from_id
     * @param to_id
     * @return
     */
    Result changeUnreadMessage(int from_id, int to_id);


    /**
     * 查找两个用户之间所有聊天记录
     * @param from_id
     * @param to_id
     * @return
     */
    List<Message> findUserAllMessageByTF(int to_id,int from_id);
}
