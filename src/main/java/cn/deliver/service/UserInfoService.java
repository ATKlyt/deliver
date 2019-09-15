package cn.deliver.service;

import cn.deliver.domain.Area;
import cn.deliver.domain.UserDriverArea;
import cn.deliver.domain.UserInfo;
import org.springframework.web.multipart.MultipartFile;

public interface UserInfoService {

    /**
     * 插入用户信息
     * @param userInfo 用户信息
     */
    void insertUserInfo(UserInfo userInfo);

    /**
     * 用户注册时填写详细信息
     * @param userInfo 用户详细信息
     * @param area 用户所属地区信息
     * @return
     */
    int registerUser(UserInfo userInfo, Area area);

    /**
     * 通过userInfoId找到用户对应的系统发放id
     * @param userInfoId userInfoId
     * @return 用户的系统发放id
     */
    String findUserAuthId(int userInfoId);

    /**
     * 注册时上传用户和司机的身份证图片文件
     * @param file 图片文件
     * @param type 图片文件的种类
     * @return 图片的url
     */
    String uploadFile(MultipartFile file, String type);

    /**
     * 修改用户个人信息
     * @param userDriverArea 存储用户信息的对象
     * @return 修改结果
     */
    int updateUserInfo(UserDriverArea userDriverArea);
}
