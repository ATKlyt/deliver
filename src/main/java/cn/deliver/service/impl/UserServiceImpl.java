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

    private final String TRANSPORT_DRIVER = "3";
    private final String PRIVATE_DRIVER = "2";
    private final String COMMON_USER = "1";

    @Autowired
    UserDao userDao;
    @Autowired
    UserInfoDao userInfoDao;
    @Autowired
    AreaDao areaDao;
    @Autowired
    UserOrderDao userOrderDao;
    @Autowired
    DriverInfoDao driverInfoDao;

    @Override
    public UserRelated findNameAndPhoneByUid(Integer uid) {
        return userDao.findNameAndPhoneByUid(uid);
    }


    @Override
    public Result findSuretyHistory(Integer userId) {
        List<UserRelated> suretyList = userDao.findSuretyHistoryByUid(userId);
        return new Result("查询成功", "0", suretyList);
    }

    @Override
    public Result findConsigneeByAuthId(String authId) {
        Map<String, Object> map = new HashMap<>(16);
        User contact = userDao.findByAuthId(authId);
        if (contact != null) {
            String contactRole = contact.getRole();
            if (TRANSPORT_DRIVER.equals(contactRole) || PRIVATE_DRIVER.equals(contactRole) || COMMON_USER.equals(contactRole)) {
                //联系人id
                //联系人电话
                //联系人名字
                map.put("contactRelate", userDao.findNameAndPhoneByUid(contact.getId()));
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
        if(/*PhoneCodeUtil.getPhoneCodeUtil().sendPhoneCode(phoneNumber,code)*/true){
            //发送成功则将验证码返回至控制层
            System.out.println("验证码：" + code);
            return "123456";
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
    public UserDriverInfo getUserDriverInfoById(User user) {
        UserDriverInfo userDriverInfo = new UserDriverInfo();
        userDriverInfo.setUser(user);
        if("0".equals(user.getRegisterRole())) {
            return userDriverInfo;
        }else{
            UserInfo userInfo = userInfoDao.getUserInfoById(user.getId());
            userDriverInfo.setUserInfo(userInfo);
            if("2".equals(user.getRegisterRole())){
                DriverInfo driverInfo  = driverInfoDao.getDriverInfoById(userInfo.getId());
                userDriverInfo.setDriverInfo(driverInfo);
            }
        }
        return userDriverInfo;
    }

    @Override
    public boolean updatePassword(String phone, String cryptographicPassword) {
        if(userDao.updatePassword(phone, cryptographicPassword) == 1){
            return true;
        }else{
            return false;
        }
    }
    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    @Override
    public User findUserById(int id) {
        User user = userDao.findUserById(id);
        return user;
    }

    @Override
    public boolean updatePersonInfo(Map<String, Object> user) {
        if(userDao.updatePersonInfo(user) == 2){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String findPhoneNumberByAuthId(String authId) {
        return userDao.findPhoneNumberByAuthId(authId);
    }

    @Override
    public Map<String,Object> register(User user) {
        //后台随机生成10位数的ID
        setUserAuthId(user);
        //md5盐值加密
        String cryptographicPassword = user.getPhone() + user.getPassword();
        user.setPassword(DigestUtils.md5DigestAsHex(cryptographicPassword.getBytes()));
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
    public User login(String id, String password) {
        return userDao.login(id,password,id.length());
    }

    /**
     * 设置注册用户的系统发放id
     * @param user
     */
    public void setUserAuthId(User user){
        String id = PhoneCodeUtil.getPhoneCodeUtil().getRandomNum(IDLENGTH);
        if(userDao.findPhoneNumberByAuthId(id) == null){
            user.setAuthId(id);
        }else{
            setUserAuthId(user);
        }
    }
}
