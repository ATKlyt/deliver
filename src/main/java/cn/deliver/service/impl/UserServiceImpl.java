package cn.deliver.service.impl;


import cn.deliver.dao.*;
import cn.deliver.domain.*;
import cn.deliver.service.UserService;
import cn.deliver.utils.ImportExcel;
import cn.deliver.utils.PhoneCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{


    private final int IDLENGTH = 10;
    private final int CODELENGTH = 6;
    private final String TRANSPORTDRIVER = "客运车司机";
    private final String PRIVATEDRIVER = "私家车司机";
    private final String COMMONUSER = "用户";

    @Autowired
    UserDao userDao;
    @Autowired
    UserInfoDao userInfoDao;
    @Autowired
    AreaDao areaDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    UserOrderDao userOrderDao;

    @Override
    public Result findDeliverInfoByAuthId(Integer uid) {
        User user = userDao.findByUid(uid);
        String userRole = user.getRole();
        Map<String, Object> map = new HashMap<>(16);
        //没有具体信息，意味着他还没有发货资格
        if (TRANSPORTDRIVER.equals(userRole) || PRIVATEDRIVER.equals(userRole) || COMMONUSER.equals(userRole)) {
            UserInfo userInfo = userInfoDao.findByUid(uid);
            map.put("phone", user.getPhone());
            map.put("name", userInfo.getName());
            return new Result("查询成功", "0", map);
        } else {
            return new Result("该用户没有发货资格", "1");
        }
    }

    @Override
    public Result findConsigneeByAuthId(String authId) {
        Map<String, Object> map = new HashMap<>(16);
        User user = userDao.findByAuthId(authId);
        if (user != null) {
            String userRole = user.getRole();
            if (TRANSPORTDRIVER.equals(userRole) || PRIVATEDRIVER.equals(userRole) || COMMONUSER.equals(userRole)) {
                UserInfo userInfo = userInfoDao.findByUid(user.getId());
                //联系人id
                map.put("cid",user.getId());
                //联系人电话
                map.put("phone", user.getPhone());
                //联系人名字
                map.put("name", userInfo.getName());
                return new Result("查询成功", "0", map);
            } else {
                //该用户没有收货资格
                return new Result("该用户没有收货资格", "1");
            }
        } else {
            //查找不到该用户
            return new Result("查找不到该用户", "1");
        }
    }


    @Override
    public List<UserDriverInfo> findAllUser() {
        return userDao.findAllUser();
    }


    /**
     * @param status 审核是否通过0代表未批，1代表审核通过，2代表审核失败
     * @param role 代表查询的角色  0代表用户，1代表司机，2代表管理员
     * */
    @Override
    public List<UserDriverInfo> findUserBySR(String status , int role) {
        User user = new User();
        System.out.println(status);
        user.setStatus(status);
        if(role == 0){
            user.setRegisterRole("用户");
            return userDao.examineFindUser(user);
        }else if(role == 1){
            user.setRegisterRole("司机");
            return userDao.examineFindDriver(user);
        }else{
            user.setRegisterRole("管理员");
            return userDao.examineFindUser(user);
        }
    }

    @Override
    public List<UserDriverInfo> findCommonUser() {
        return userDao.findCommonUser();
    }

    /**
     * 导入Excel表格
     * */
    @Override
    public void importExcel(InputStream inputStream, String fileName) throws IOException, ParseException {

        List<UserDriverInfo> userAndUserInfos = ImportExcel.creatList(inputStream, fileName);
        UserInfo userInfo;
        for(UserDriverInfo userDriverInfo:userAndUserInfos){
            userDao.insertUser(userDriverInfo.getUser());
            int uid = userDriverInfo.getUser().getId();
            userInfo = userDriverInfo.getUserInfo();
            userInfo.setUid(uid);
            userInfoDao.insertUserInfo(userInfo);
        }
    }

    /**
     * 构造可以插入Excel表格的对象
     * */
    @Override
    public List<String[]> exportExcel(List<UserDriverInfo> userAndUserInfo) {

        List<String[]> resource = new ArrayList<String[]>();

        resource.add(new String[]{"用户Id","用户账号","手机","角色","注册时间","注册区域","邮箱","姓名","性别","生日"});

        for (UserDriverInfo index :userAndUserInfo){
            resource.add(new String[]{index.getUser().getId()+"",index.getUser().getAuthId(),index.getUser().getPhone(),index.getUser().getRole(),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(index.getUser().getRegisterTime()),
                    index.getUser().getRegisterArea(),
                    index.getUser().getEmail(),index.getUserInfo().getName(),index.getUserInfo().getGender(),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(index.getUserInfo().getBirthday())});
        }

        return resource;
    }

    /**
     * 审核用户状态
     * @param id 用户id
     * @param status 审核状态
     * */
    @Override
    public void updateUserStatus(int id, String status,String role) {
        if (Integer.parseInt(status) == 1){
            userDao.updateUserStatus(id,status,role);
        }else {
            userDao.updateUserStatus(id,status,"游客");
        }
    }

    @Override
    public boolean checkPhoneNumber(String phoneNumber) {
        if(userDao.checkPhoneNumber(phoneNumber)!=null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String getPhoneCode(String phoneNumber){
        //后台随机生成6位数验证码
        String code = PhoneCodeUtil.getPhoneCodeUtil().getRandomNum(CODELENGTH);
        //调用工具类发送手机验证码
        if(PhoneCodeUtil.getPhoneCodeUtil().sendPhoneCode(phoneNumber,code)){
            //发送成功则将验证码返回至控制层
            System.out.println(code);
            return code;
        }else{
            return null;
        }
    }

    /**
     * 模糊查询
     * @param info 查询内容
     * @return
     */
    @Override
    public List<UserDriverInfo> abstractQuery(String info) {
        List<UserDriverInfo> userDriverInfos = userDao.abstractQuery(info);
        return userDriverInfos;
    }

    /**
     * 根据Id批量删除用户
     * @param map
     * @return
     */
    @Override
    public Result deleteUser(Map<String, Object> map) {
        int id = Integer.parseInt((String) map.get("id"));
        int num = userDao.deleteByPrimaryKey(id);
        if (num > 0){
            return new Result("删除成功","0",null);
        }else {
            return new Result("用户ID不存在","1",null);
        }
    }

    @Override
    public String findPhoneNumberByAuthId(String authId) {
        return userDao.findPhoneNumberByAuthId(authId);
    }

    @Override
    public UserInfo findUserDataById(String id ,int length) {
        return userDao.findUserDataById(id,length);
    }

    @Override
    public boolean updatePassword(String phoneNumber, String password) {
        password = phoneNumber + password;
        String cryptographicPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        if(userDao.updatePassword(phoneNumber,cryptographicPassword) == 1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Map<String,Object> register(User user) {
        //后台随机生成10位数的ID
        user.setAuthId(PhoneCodeUtil.getPhoneCodeUtil().getRandomNum(IDLENGTH));
        //md5盐值加密
        String cryptographicPassword = user.getPhone() + user.getPassword();
        user.setPassword(DigestUtils.md5DigestAsHex(cryptographicPassword.getBytes()));
        //未通过后台审核前所有用户的身份均为游客
        user.setRole("游客");
        //审核状态("0"为审核中，"1"为审核通过，"2"为审核不通过)
        user.setStatus("0");
        user.setRegisterTime(new Timestamp(System.currentTimeMillis()));
        Map<String,Object> result = new HashMap<>(2);
        if(userDao.register(user)>0){
            result.put("userAuthId",user.getAuthId());
            result.put("userId",user.getId());
        }
        return result;
    }

    @Override
    public Integer login(String id, String password) {
        return userDao.login(id,password,id.length());
    }
}
