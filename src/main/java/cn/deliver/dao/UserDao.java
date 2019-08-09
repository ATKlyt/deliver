package cn.deliver.dao;

import cn.deliver.domain.User;
import cn.deliver.domain.UserDriverInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserDao {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findByUid(Integer uid);

    User findByAuthId(String authId);

    //==================================艺明=====================================

    /**
     * 查询所有的用户信息
     * */
    List<UserDriverInfo> findAllUser();

    /**
     * 查找普通用户
     * */
    List<UserDriverInfo> findCommonUser();

    /**
     * Excel表格插入数据
     * */
    int insertUser(User user);

    /**
     * 审核更改申请状态   1为通过，2为失败
     * */
    void updateUserStatus(@Param("id") int id , @Param("status") String status);

    /**
     * 审核时查找用户或者管理员
     * */
    List<UserDriverInfo> examineFindUser(User user);

    /**
     * 查找正在审核的司机
     * */
    List<UserDriverInfo> examineFindDriver(User user);

//==================================俊彬=====================================
    /**
     * 检测手机号码是否已被注册
     * @param phoneNumber 手机号码
     * @return 用户id
     */
    Integer checkPhoneNumber(@Param("phoneNumber") String phoneNumber);

    /**
     * 注册用户
     * @param user 存储前端用户填写信息
     */
    int register(User user);

    /**
     * 用户登录
     * @param id 用户id或手机号码
     * @param password 用户密码
     * @param length 判断用户是使用id登录还是手机号码登录
     * @return 用户id
     */
    Integer login(@Param("id") String id,@Param("password") String password,@Param("length") int length);


    String findPhoneByUid(Integer cid);
}