package cn.deliver.controller;

import cn.deliver.domain.Area;
import cn.deliver.domain.DriverInfo;
import cn.deliver.domain.Result;
import cn.deliver.domain.UserInfo;
import cn.deliver.service.DriverInfoService;
import cn.deliver.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private DriverInfoService driverInfoService;

    private final String DRIVER = "司机";
    private final String USER = "用户";
    private final String IDENTITYCARDFORMAT = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";

    /**
     * 用户和司机注册时填写详细信息
     * @param userInfo 存储用户和司机注册填写的详细信息
     * @param driverInfo 存储司机注册时额外填写的详细信息
     * @param area 存储用户填写所属区域的信息
     * @param request 封装HTTP请求的对象
     * @return 结果集
     */
    @RequestMapping(value = "/userInfoRegister",method = RequestMethod.POST)
    @ResponseBody
    public Result userInfoRegister(UserInfo userInfo, DriverInfo driverInfo, Area area, HttpServletRequest request){
        if(userInfo.getUid() == null ||userInfo.getName() == null ||area.getProvince() == null ||area.getCity() == null ||area.getDistrict() == null ||area.getStatus() == null ||userInfo.getIdentityCard() == null ){
            return new Result("用户注册必填项信息为空","1",null);
        }
        if(!userInfo.getIdentityCard().matches(IDENTITYCARDFORMAT)){
            return new Result("身份证格式有误","1",null);
        }
        //获取注册的角色
        String role = request.getParameter("role");
        if(role == null){
            return new Result("用户暂未选择身份","1",null);
        }
        //普通用户与司机注册经过的共同步骤(姓名、性别、生日、身份证号码)
        int userInfoId = userInfoService.registerUser(userInfo,area);
        //判断用户信息是否被插入
        if(userInfoId > 0){
            //通过主键id获取用户的系统发放id，用于返回前端提示用户可使用系统发放id或手机号码进行登录操作
            String userAuthId = userInfoService.findUserAuthId(userInfoId);
            if(USER.equals(role)) {
                return new Result("用户注册成功", "0", userAuthId);
            }else if(DRIVER.equals(role)){
                if(driverInfo.getCarType() == null ||driverInfo.getDrivingLicencePicture() == null ||driverInfo.getCarPicture() == null ){
                    return new Result("司机注册必填项信息为空","1",null);
                }
                //将user_info的主键id赋值给实体类driverInfo的uiid属性以绑定user_info表中对应记录
                driverInfo.setUiid(userInfoId);
                //司机所需提供额外的注册信息
                if(driverInfoService.registerDriver(driverInfo)){
                    return new Result("司机注册成功","0",userAuthId);
                }else{
                    return new Result("司机注册失败","1",null);
                }
            }else{
                return new Result("获取身份失败，请重新注册","1",null);
            }
        }else{
            return new Result("用户注册失败","1",null);
        }
    }

    /**
     * 上传身份证拍摄图片
     * @param file 图片文件
     * @param type 图片种类
     * @return 结果集
     */
    @RequestMapping("/identityCardPicture")
    @ResponseBody
    public Result uploadDrivingLicence(MultipartFile file ,String type){
        String url = userInfoService.uploadFile(file,type);
        if(url != null){
            return new Result("身份证上传成功","0",url);
        }else{
            return new Result("身份证上传失败","1",null);
        }
    }

    /**
     * 上传头像
     * @param file 图片文件
     * @param type 图片种类
     * @return 结果集
     */
    @RequestMapping("/headPortraits")
    @ResponseBody
    public Result uploadHeadPortraits(MultipartFile file ,String type){
        String url = userInfoService.uploadFile(file,type);
        if(url != null){
            return new Result("头像上传成功","0",url);
        }else{
            return new Result("头像上传失败","1",null);
        }
    }
}
