package cn.deliver.controller;

import cn.deliver.domain.Result;
import cn.deliver.service.DriverInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/driverInfo")
public class DriverInfoController {

    @Autowired
    private DriverInfoService driverInfoService;

    /**
     * 上传驾驶证拍摄图片
     * @param file 图片文件
     * @param type 图片种类
     * @return 结果集
     */
    @RequestMapping("/drivingLicence")
    @ResponseBody
    public Result uploadDrivingLicence(MultipartFile file , String type){
        String url = driverInfoService.uploadFile(file,type);
        if(url != null){
            return new Result("驾驶证上传成功","0",url);
        }else{
            return new Result("驾驶证上传失败","1",null);
        }
    }

    /**
     * 上传汽车照片拍摄图片
     * @param file 图片文件
     * @param type 图片种类
     * @return 结果集
     */
    @RequestMapping("/CardPicture")
    @ResponseBody
    public Result uploadCarPicture(MultipartFile file ,String type){
        String url = driverInfoService.uploadFile(file,type);
        if(url != null){
            return new Result("汽车照片上传成功","0",url);
        }else{
            return new Result("汽车照片上传失败","1",null);
        }
    }
}
