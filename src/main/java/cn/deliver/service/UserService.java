package cn.deliver.service;

import cn.deliver.domain.Result;
import cn.deliver.domain.User;
import cn.deliver.domain.UserDriverInfo;
import cn.deliver.domain.UserRelated;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface UserService {



    Result findDeliverInfoByAuthId(Integer uid);

    Result findConsigneeByAuthId(String authId);
    /**
     * 查询所有的用户
     * */
    List<UserDriverInfo> findAllUser();

    /**
     * 查找普通用户（只包括已经审核通过的用户）
     * */
    List<UserDriverInfo> findCommonUser();

    /**
     * 查询处于声明状态和角色的用户（未审核，审核通过，审核失败）
     *
     **/
    List<UserDriverInfo> findUserBySR(String status,int role);


    /**
     * 导入excel表格数据
     * */
    void importExcel(InputStream inputStream, String fileName) throws IOException, ParseException;

    /**
     * 导出数据库
     * */
    List<String[]> exportExcel(List<UserDriverInfo> userAndUserInfo);

    /**
     * 审核用户申请
     * */
    void updateUserStatus(int id, String status,String role);


    /**
     * 检测手机号码是否已被注册
     * @param phoneNumber 手机号码
     * @return 检测结果
     */
    boolean checkPhoneNumber(String phoneNumber);

    /**
     * 注册用户
     * @param user 存储前端用户填写信息
     * @return 注册结果
     */
    Map<String,Object> register(User user);

    /**
     * 用户登录
     * @param id 用户id或手机号码
     * @param password 用户密码
     * @return 用户登录信息
     */
    User login(String id, String password);

    /**
     * 获取手机验证码
     * @param phoneNumber 用户手机号码
     * @return 验证码
     */
    String getPhoneCode(String phoneNumber);

    /**
     * 通过系统发放id获取用户的手机号码
     * @param authId 系统发放id
     * @return 用户的手机号码
     */
    String findPhoneNumberByAuthId(String authId);

    /**
     * 查询内容
     * @param info
     * @return
     */
    List<UserDriverInfo> abstractQuery(String info);

    /**
     * 根据ID删除用户
     * @param map
     * @return
     */
    Result deleteUser(Map<String, Object> map);

    /**
     * 根据用户登录信息获取用户个人信息
     * @param user 用户登录信息
     * @return 用户个人信息
     */
    UserDriverInfo getUserDriverInfoById(User user);

    /**
     * 修改密码
     * @param phone 手机号码
     * @param cryptographicPassword 加盐密码
     * @return 操作结果
     */
    boolean updatePassword(String phone, String cryptographicPassword);

    /**
     * 修改用户资料
     * @param user 用户资料
     * @return 修改结果
     */
    boolean updatePersonInfo(Map<String, Object> user);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    User findUserById(int id);

    UserRelated findNameAndPhoneByUid(Integer uid);
}
