package cn.deliver.service.impl;

import cn.deliver.dao.AreaDao;
import cn.deliver.dao.DriverInfoDao;
import cn.deliver.dao.UserInfoDao;
import cn.deliver.domain.Area;
import cn.deliver.domain.UserDriverArea;
import cn.deliver.domain.UserInfo;
import cn.deliver.service.UserInfoService;
import cn.deliver.utils.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private DriverInfoDao driverInfoDao;
    @Autowired
    private AreaDao areaDao;

    @Override
    public void insertUserInfo(UserInfo userInfo) {
        userInfoDao.insertUserInfo(userInfo);
    }

    private static final String AVATAR = "";

    @Override
    public int registerUser(UserInfo userInfo, Area area) {
        if(userInfoDao.userInfoRegister(userInfo)>0) {
            //将用户注册填写的所属地区存入area表中
            areaDao.insertUserArea(area);
            return userInfo.getId();
        }else{
            return 0;
        }
    }

    @Override
    public String findUserAuthId(int userInfoId) {
        return userInfoDao.findUserAuthId(userInfoId);
    }

    @Override
    public String uploadFile(MultipartFile file, String type) {
        return UploadFileUtil.uploadFile(file,type);
    }

    @Override
    public int updateUserInfo(UserDriverArea userDriverArea) {
        return 0;
    }
}
