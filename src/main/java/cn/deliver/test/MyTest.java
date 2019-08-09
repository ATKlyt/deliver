package cn.deliver.test;

import cn.deliver.dao.DriverInfoDao;
import cn.deliver.dao.UserDao;
import cn.deliver.dao.UserInfoDao;
import cn.deliver.domain.DriverInfo;
import cn.deliver.domain.User;
import cn.deliver.domain.UserInfo;
import cn.deliver.utils.NameBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author Ming
 * @date 2019/8/6 - 22:15
 */
public class MyTest {

    ApplicationContext applicationcontext;

    public void init() {
        // 初始化Spring容器
        applicationcontext = new ClassPathXmlApplicationContext("file:src/main/resources/ApplicationContext.xml");
    }

    public void getUserText2() {
        UserDao userDao = applicationcontext.getBean(UserDao.class);
        UserInfoDao userInfoDao = applicationcontext.getBean(UserInfoDao.class);
        DriverInfoDao driverInfoDao = applicationcontext.getBean(DriverInfoDao.class);

        User user;
        UserInfo userInfo;
        DriverInfo driverInfo;
        for (int i = 0 ; i < 10 ;i++){
            int account = (int)((Math.random()*9+1)*100000);
            int number = (int)((Math.random()*9+1)*100000000);
            user = new User(null,account+"","13"+ number,"123","1","司机",
                    new Timestamp(System.currentTimeMillis()),"贵州","司机",number + "@qq.com");
            if (i % 2 == 0) {
                userInfo = new UserInfo(null, null, NameBuilder.build(2),"男",new Date(System.currentTimeMillis()),"no","no","/pic/nopicture.jpg");
            }else {
                userInfo = new UserInfo(null, null, NameBuilder.build(2),"女",new Date(System.currentTimeMillis()),"no","no","/pic/nopicture.jpg");
            }
            userDao.insertUser(user);
            userInfo.setUid(user.getId());
            userInfoDao.userInfoRegister(userInfo);
            if (i % 3 == 0) {
                driverInfo = new DriverInfo(null,userInfo.getId(),"/pic/nopicture.jpg","/pic/nopicture.jpg","no",0,"货车",account+"","0");
            }else {
                driverInfo = new DriverInfo(null,userInfo.getId(),"/pic/nopicture.jpg","/pic/nopicture.jpg","no",0,"私家车",account+"","0");
            }
            driverInfo.setUiid(userInfo.getId());
            System.out.println(driverInfo);
            driverInfoDao.driverInfoRegister(driverInfo);
        }
//        System.out.println(userInfo);
//        System.out.println(user);

    }

}
