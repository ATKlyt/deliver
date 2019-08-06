package cn.deliver.service;

import cn.deliver.domain.Area;
import cn.deliver.domain.DriverInfo;
import cn.deliver.domain.UserInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserInfoService {
    //=========================================艺明===================
    //插入用户信息
    void insertUserInfo(UserInfo userInfo);


//==================================俊彬=====================================
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
}
