package cn.deliver.dao;

import cn.deliver.domain.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Ming
 * @date 2019/10/16 - 23:00
 */
public interface ChatDao {

    /**
     * 插入聊天信息
     * @param message 插入的信息
     * @return 更改条数
     */
    int insertMessage(Message message);

    /**
     * 根据用户的id查找未读的聊天记录
     * @param to_id 登录的用户
     * @param status 未读状态
     * @return 所有信息
     */
    List<Message> findUserAllMessageByTS(@Param("to_id") int to_id, @Param("status") int status);

    /**
     * 查询用户A与用户B中未读的信息
     * @param from_id 来来自的用户
     * @param to_id 发给的用户
     * @return 返回信息
     */
    List<Message> findUserAllMessageByFTS(@Param("from_id") int from_id, @Param("to_id") int to_id);

    /**
     * 更改用户未读信息状态
     * @param id 信息id
     * @param status 更改为的状态
     */
    void changeMessageStatus(@Param("id") int id,@Param("status") int status);

    /**
     * 查找两个用户之间所有聊天记录
     * @param from_id 来自用户
     * @param to_id  发给用户
     * @return
     */
    List<Message> findUserAllMessageByTF(@Param("to_id") int to_id,@Param("from_id") int from_id);
}
