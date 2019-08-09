package cn.deliver.dao;

import cn.deliver.domain.UserInfo;
import cn.deliver.domain.UserInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoDao {
    long countByExample(UserInfoExample example);

    int deleteByExample(UserInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    List<UserInfo> selectByExample(UserInfoExample example);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    List<UserInfo> selectSurety(Integer uid);

    UserInfo findByUid(Integer uid);

    //==================================艺明=====================================
    //插入用户信息
    void insertUserInfo(UserInfo userInfo);


    //==================================俊彬=====================================
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

    String findNameByUid(Integer cid);
}