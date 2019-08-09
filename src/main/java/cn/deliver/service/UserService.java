package cn.deliver.service;

import cn.deliver.domain.Result;
import cn.deliver.domain.User;
import cn.deliver.domain.UserDriverInfo;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface UserService {



    Map<String, Object> findShipperInfoByAuthId(Integer uid);

    Map<String, Object> findConsigneeByAuthId(String authId);


    //=========================================艺明===================

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

    //==================================俊彬=====================================


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
     * @return 登录结果
     */
    Integer login(String id, String password);

    /**
     * 获取手机验证码
     * @param phoneNumber 用户手机号码
     * @return 验证码
     */
    String getPhoneCode(String phoneNumber);

    /**
     * @param info 查询内容
     * */
    List<UserDriverInfo> abstractQuery(String info);

    /**
     * 根据ID删除用户
     * @param map
     * @return
     */
    Result deleteUser(Map<String, Object> map);
}
