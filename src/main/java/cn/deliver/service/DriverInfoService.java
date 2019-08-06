package cn.deliver.service;

import cn.deliver.domain.DriverInfo;
import org.springframework.web.multipart.MultipartFile;

public interface DriverInfoService {
    /**
     * 注册时上传司机的图片文件
     * @param file 图片文件
     * @param type 图片文件的种类
     * @return 图片的url
     */
    String uploadFile(MultipartFile file, String type);

    /**
     * 司机注册时填写详细信息
     * @param driverInfo 司机详细信息
     * @return
     */
    boolean registerDriver(DriverInfo driverInfo);
}
