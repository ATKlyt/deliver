package cn.deliver.service.impl;

import cn.deliver.dao.DriverInfoDao;
import cn.deliver.domain.DriverInfo;
import cn.deliver.service.DriverInfoService;
import cn.deliver.utils.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DriverInfoServiceImpl implements DriverInfoService {


    @Autowired
    private DriverInfoDao driverInfoDao;

    @Override
    public String uploadFile(MultipartFile fil, String type) {
        return UploadFileUtil.uploadFile(fil,type);
    }

    @Override
    public boolean registerDriver(DriverInfo driverInfo) {
        if(driverInfoDao.driverInfoRegister(driverInfo)>0){
            return true;
        }else{
            return false;
        }
    }

}
