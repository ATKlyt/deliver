package cn.deliver.controller;

import cn.deliver.domain.Result;
import cn.deliver.domain.User;
import cn.deliver.domain.UserDriverInfo;
import cn.deliver.service.UserService;
import cn.deliver.utils.ExportExcel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 管理员删除用户
     * @param map 封装 id
     * @return
     */
    @RequestMapping(value = "deleteUser")
    @ResponseBody
    public Result deleteUser(@RequestBody Map<String , Object> map){
        Result result = userService.deleteUser(map);
        return result;
    }

    /**
     * 模糊查询
     * @param map 请求参数
     * */
    @RequestMapping(value = "/abstractQuery")
    @ResponseBody
    public Result abstractQuery(@RequestBody Map<String,Object> map ) {

        int pn = Integer.parseInt((String)map.get("pn"));
        String info = (String) map.get("info");
        List<UserDriverInfo> userDriverInfos = userService.abstractQuery(info);
        if (userDriverInfos!=null){
            PageHelper.startPage(pn,5);
            PageInfo page = new PageInfo(userDriverInfos,5);
            return new Result("找到相关用户消息","0",page);
        }else {
            return new Result("未查找到用户消息","0",null);
        }
    }


    /**
     * 获取发货人信息
     * @param uid
     * @return
     */
    @ResponseBody
    @RequestMapping("findDeliverInfo")
    public Result findDeliverInfoByAuthId(Integer uid){
        return userService.findDeliverInfoByAuthId(uid);
    }

    /**
     * 获取收货人信息
     * @param authId
     * @return
     */
    @ResponseBody
    @RequestMapping("findConsigneeInfo")
    public Result findConsigneeByAuthId(String authId){
        return userService.findConsigneeByAuthId(authId);
    }

    /**
     * 查询分页信息
     * */
    @RequestMapping(value="/pageUser")
    @ResponseBody
    public Result findCommonUser(@RequestParam(value="pn",defaultValue="1")Integer pn) {
        //页码pn以及数据数5
        PageHelper.startPage(pn,5);
        //紧跟着的一个查询
        List<UserDriverInfo> userPage = userService.findAllUser();
        //包装查询结果,连续显示五页
        PageInfo page = new PageInfo(userPage,5);
        return new Result("处理成功","0",page);
    }

    /**
     * @param status 审核是否通过 0代表未批，1代表审核通过，2代表审核失败
     * @param role 代表查询的角色  0代表用户，1代表司机，2代表管理员
     * */
    @ResponseBody
    @RequestMapping(value = "/audit",method = RequestMethod.GET)
    public Result auditUser(@RequestParam(value = "status",defaultValue = "0") String status,@RequestParam(value = "role") int role,@RequestParam(value = "pn",defaultValue = "1") int pn){
        List<UserDriverInfo> searchList = userService.findUserBySR(status,role);
        PageHelper.startPage(pn,5);
        //包装查询结果,连续显示五页
        PageInfo page = new PageInfo(searchList,5);
        return new Result("处理成功","0",page);

    }


    /**
     * 导出数据
     * */
    @RequestMapping(value = "/exportExcel",method = RequestMethod.GET)
    @ResponseBody
    public Result exportExcel() throws IOException {

        List<UserDriverInfo> userAndUserInfo = userService.findCommonUser();
        ExportExcel exportExcel = new ExportExcel();
        List<String[]>  resource = userService.exportExcel(userAndUserInfo);
        String name = UUID.randomUUID().toString();
        String newName = "C:\\picture\\"+name+".xlsx";
        OutputStream outputStream = new FileOutputStream(newName);
        exportExcel.exportExcel(resource,outputStream);
        outputStream.flush();
        outputStream.close();
        return new Result("导出成功","0",newName);
    }

    /**
     * 下载文件
     * 支持在线预览
     * @param filePath 路径位置
     * @param isOnLine 是否要支持在线预览
     * */
    @RequestMapping(value = "/downLoad" , method = RequestMethod.GET)
    public void downLoad(String filePath ,boolean isOnLine , HttpServletResponse response) throws Exception{
        File f = new File(filePath);
        if (!f.exists()) {
            response.sendError(404, "路径错误！！！");
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;
        // 非常重要
        response.reset();

        if(isOnLine){
            response.setHeader("Content-Disposition", "inline; filename=" + f.getName());
        }else{
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
        }

        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0){
            out.write(buf, 0, len);
        }
        br.close();
        out.close();
    }



    /**
     * 导入数据
     * */
    @ResponseBody
    @RequestMapping(value = "uploadExcel",method = RequestMethod.POST)
    public Result uploadExcel(MultipartFile file)  {
        //判断是xls还是xlsx
        String fileName = file.getOriginalFilename();
        if(!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")){
            return new Result("文件格式错误","100",null);
        }

        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            userService.importExcel(inputStream,fileName);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return new Result("导入失败","1",null);
        }

        return new Result("导入成功","0",null);
    }

    /**
     * 审核用户申请
     */
    @RequestMapping("/updateUserStatus")
    @ResponseBody
    public Result updateUserStatus(@RequestBody Map<String , Object> map){
        String id = (String) map.get("id");
        String status = (String) map.get("status");
        String role = (String) map.get("role");
        userService.updateUserStatus(Integer.parseInt(id),status,role);
        return new Result("审核成功","0",null);
    }



    private final int CODETIME = 60;
    private final String PASSWORDFORMAT = "(?!.*[\\u4E00-\\u9FA5\\s])(?!^[a-zA-Z]+$)(?!^[\\d]+$)(?!^[^a-zA-Z\\d]+$)^.{6,16}$";
    private final String PHONENUMBERFORMAT = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$";

    /**
     * 检测手机号码是否被注册
     * @param data 存储手机号码的map集合
     * @return 结果集
     */
    @RequestMapping(value = "/checkPhone",method = RequestMethod.POST)
    @ResponseBody
    public Result checkPhoneNumber(@RequestBody Map<String,Object> data){
        String phoneNumber = (String) data.get("phone");
        if(phoneNumber == null || !phoneNumber.matches(PHONENUMBERFORMAT)){
            return new Result("手机号码为空或格式有误","1",null);
        }
        if(userService.checkPhoneNumber(phoneNumber)){
            return new Result("该手机号码已被注册","1",null);
        }else{
            return new Result("该手机号码未被注册","0",null);
        }
    }

    /**
     * 发送手机验证码
     * @param data 用户手机信息
     * @param request 封装HTTP请求的对象
     * @return 结果集
     */
    @RequestMapping(value = "/getPhoneCode",method = RequestMethod.POST)
    @ResponseBody
    public Result getPhoneCode(@RequestBody Map<String,Object> data,HttpServletRequest request){
        String phoneNumber = (String) data.get("phone");
        //验证手机号码是否为空或符合格式
        if(phoneNumber == null || !phoneNumber.matches(PHONENUMBERFORMAT)){
            return new Result("手机号码为空或格式有误","1",null);
        }
        String code = userService.getPhoneCode(phoneNumber);
        System.out.println(code);
        if(code!=null){
            //将随机生成的验证码存入session域中等待比对
            request.getSession().setAttribute("beforeCode",code);
            request.getSession().setAttribute("sendTime",new Date());
            return new Result("验证码发送成功","0",null);
        }else{
            return new Result("验证码发送失败","1",null);
        }
    }

    /**
     * 验证手机验证码
     * @param data 用户验证码信息
     * @param request 封装HTTP请求的对象
     * @return 结果集
     */
    @RequestMapping(value = "/checkPhoneCode",method = RequestMethod.POST)
    @ResponseBody
    public Result checkCode(@RequestBody Map<String,Object> data,HttpServletRequest request){
        String code = (String) data.get("code");
        if(code == null){
            return new Result("验证码为空","1",null);
        }
        String beforeCode = (String) request.getSession().getAttribute("beforeCode");
        Date sendTime = (Date) request.getSession().getAttribute("sendTime");
        //计算时间差，超过60秒即失效
        System.out.println("sendTime:"+sendTime);
        long diffTime = (System.currentTimeMillis() - sendTime.getTime()) / 1000;
        if(diffTime < CODETIME) {
            if (code.equals(beforeCode)) {
                return new Result("验证码正确", "0", null);
            } else {
                return new Result("验证码错误", "1", null);
            }
        }else {
            return new Result("验证码已过期，请重新发送验证码", "1", null);
        }
    }

    /**
     * 注册用户
     * @param user 存储前端用户注册填写的信息
     * @return 结果集
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Result userRegister(@RequestBody User user){
        if(user.getPhone() == null|| user.getPassword() == null|| user.getRegisterRole() == null){
            return new Result("用户必填项信息为空","1",null);
        }
        if(!user.getPhone().matches(PHONENUMBERFORMAT)){
            return new Result("手机号码格式有误","1",null);
        }
        if(!user.getPassword().matches(PASSWORDFORMAT)){
            return new Result("密码格式有误","1",null);
        }
        Map<String,Object> result = userService.register(user);
        if((boolean) result.get("result")){
            return new Result("注册成功","0",result.get("userAuthId"));
        }else{
            return new Result("注册失败","1",null);
        }
    }

    /**
     * 用户登录功能
     * @param data 用户登录信息
     * @return 结果集
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result userLogin(@RequestBody Map<String,Object> data, HttpServletRequest request){
        String id = (String) data.get("id");
        if(!((String) data.get("password")).matches(PASSWORDFORMAT)){
            return new Result("密码格式有误","1",null);
        }
        //简单的MD5加密，后续添加盐值加密
        String password = DigestUtils.md5DigestAsHex(((String)data.get("password")).getBytes());
        Integer loginId = userService.login(id,password);
        if(loginId!=null){
            request.getSession().setAttribute("id",loginId);
            return new Result("登录成功","0",id);
        }else{
            return new Result("登录失败","1",null);
        }
    }

}
