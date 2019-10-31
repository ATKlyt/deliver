package cn.deliver.dao;

import cn.deliver.domain.UserInfo;

import java.util.List;

public interface UserInfoDao {

    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    List<UserInfo> selectSurety(Integer uid);

    /**
     * 通过uid查找用户信息
     * @param uid
     * @return
     */
    UserInfo findByUid(Integer uid);

    /**
     * 插入用户信息
     * @param userInfo
     * @return
     */
    int insertUserInfo(UserInfo userInfo);


    /**
     * 用户注册时填写详细信息
     * @param userInfo 用户详细信息
     * @return
     */
    int userInfoRegister(UserInfo userInfo);

    /**
     * 通过userInfoId找到用户对应的系统发放id
     * @param userInfoId 用户的userInfoId
     * @return 用户的系统发放id
     */
    String findUserAuthId(int userInfoId);

    String findNameByUid(Integer uid);

    /**
     * 根据用户id获取UserInfo表信息
     * @param id 用户id
     * @return 对应UserInfo表信息
     */
    UserInfo getUserInfoById(Integer id);

    /**
     * 修改用户个人信息
     * @param userInfo 存储用户个人信息的对象
     * @return 修改结果
     */
    int updateUserInfo(UserInfo userInfo);
}