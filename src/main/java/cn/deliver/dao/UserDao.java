package cn.deliver.dao;

import cn.deliver.domain.User;
import cn.deliver.domain.UserDriverInfo;
import cn.deliver.domain.UserRelated;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {

    int deleteByPrimaryKey(int id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    /**
     * 通过系统发放id查找用户
     * @param authId
     * @return
     */
    User findByAuthId(String authId);

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
    void updateUserStatus(@Param("id") int id , @Param("status") String status,@Param("role") String role);

    /**
     * 审核时查找用户或者管理员
     * */
    List<UserDriverInfo> examineFindUser(User user);

    /**
     * 查找正在审核的司机
     * */
    List<UserDriverInfo> examineFindDriver(User user);

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
     * @return 用户登录信息
     */
    User login(@Param("id") String id,@Param("password") String password,@Param("length") int length);

    /**
     * 通过系统发放id获取用户的手机号码
     * @param authId 系统发放id
     * @return 用户的手机号码
     */
    String findPhoneNumberByAuthId(@Param("authId") String authId);
    
    /**
     * 模糊查询
     * @param info
     * @return
     */
    List<UserDriverInfo> abstractQuery(@Param("info") String info);

    /**
     * 根据uid查找电话号码
     * @param cid
     * @return
     */
    String findPhoneByUid(Integer cid);

    /**
     * 修改密码
     * @param phone 手机号码
     * @param cryptographicPassword 加盐密码
     * @return 操作结果
     */
    int updatePassword(@Param("phone") String phone, @Param("password") String cryptographicPassword);

    /**
     * 修改用户资料
     * @param user 用户资料
     * @return 修改结果
     */
    int updatePersonInfo(@Param("user") Map<String, Object> user);
    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    User findUserById(int id);

    /**
     * 通过用户id查找姓名和电话
     * @param uid
     * @return
     */
    UserRelated findNameAndPhoneByUid(Integer uid);

    /**
     * 通过用户id查找最近使用过的十条担保人记录
     * @param userId
     * @return
     */
    List<UserRelated> findSuretyHistoryByUid(Integer userId);
}