package cn.deliver.test;

import cn.deliver.dao.ChatDao;
import cn.deliver.domain.Message;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Ming
 * @date 2019/10/17 - 16:31
 */
public class ChatTest {

    ApplicationContext applicationContext;

    @Before
    public void init(){
        applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    }

    @Test
    public void InsertMessage(){
        ChatDao chatDao = applicationContext.getBean(ChatDao.class);
        List<Message> messageList = chatDao.findUserAllMessageByTS(11,1);
//        for (Message message : messageList){
//            System.out.println(message);
//        }
        Message message = null;
        for (int i = 1;i<154;i++) {
            message = new Message();
            message.setFrom((int) (Math.random() * 9 + 1) * 1);
            message.setTo((int) (Math.random() * 9 + 1) * 1);
            message.setMessage(new Timestamp(System.currentTimeMillis()).toString());
            message.setDate(new Timestamp(System.currentTimeMillis()));
            message.setType(1);
            message.setStatus(1);
            chatDao.insertMessage(message);
//            chatDao.changeMessageStatus(i,0);
        }
    }


}
