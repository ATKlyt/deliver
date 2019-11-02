package cn.deliver.service.impl;

import cn.deliver.dao.*;
import cn.deliver.domain.Area;
import cn.deliver.domain.Result;
import cn.deliver.domain.User;
import cn.deliver.domain.UserOrder;
import cn.deliver.service.UserOrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserOrderServiceImpl implements UserOrderService {
    @Autowired
    UserOrderDao userOrderDao;
    @Autowired
    AreaDao areaDao;
    @Autowired
    UserDao userDao;
    @Autowired
    UserInfoDao userInfoDao;
    @Autowired
    InvitationDao invitationDao;


    /**
     * 分页,每一页有5条数据
     */
    private final Integer PAGE_SIZE = 5;
    /**
     * 用户订单等待被担保人确认/拒绝
     */
    private static final String WAIT_SURETY = "0";
    /**
     * 用户订单等待被司机接受
     */
    private static final String WAIT_RECEIVE = "1";
    /**
     * 用户订单等待被收货人确认收货
     */
    private static final String WAIT_CONTACT = "3";
    /**
     * 身份：客运车司机
     */
    private static final String TRANSPORT_DRIVER = "3";
    /**
     * 身份：司机车司机
     */
    private static final String PRIVATE_DRIVER = "2";
    /**
     * 身份：普通用户
     */
    private static final String COMMON_USER = "1";

    @Override
    public UserOrder selectByPrimaryKey(Integer userOrderId) {
        return userOrderDao.selectByPrimaryKey(userOrderId);
    }

    @Override
    public Result orderRelease(UserOrder userOrder) {
        //用户身份
        String userRole = userDao.selectByPrimaryKey(userOrder.getUid()).getRole();
        if (TRANSPORT_DRIVER.equals(userRole) || PRIVATE_DRIVER.equals(userRole) || COMMON_USER.equals(userRole)){
            if (userOrderDao.insertSelective(userOrder) > 0) {
                return new Result("发单成功", "0", userOrder.getId());
            } else {
                return new Result("发单失败", "1");
            }
        }else{
            return new Result("该用户没有发单资格", "1");
        }

    }


    @Override
    public Result validateSurety(Integer suretyId, Integer shipperId) {
        User surety = userDao.selectByPrimaryKey(suretyId);
        if (surety == null){
            return new Result("该用户不存在", "1");
        }else if (suretyId.equals(shipperId)){
            return new Result("担保人不能是自己", "1");
        }
        //担保人所属地址
        Area suretyBelong = areaDao.findAreaByUidAndType(suretyId, "1");
        //发货人所属地址
        Area shipperBelong = areaDao.findAreaByUidAndType(shipperId, "1");
        if (suretyBelong.getDistrict().equals(shipperBelong.getDistrict())){
            if (suretyBelong.getTown().equals(shipperBelong.getTown())){
                if (suretyBelong.getVillage().equals(shipperBelong.getVillage())){
                    //存储担保人信息
                    Map<String, Object> map = new HashMap<>(16);
                    /*
                        担保人电话
                        担保人id
                        担保人名字
                     */
                    map.put("suretyRelate", userDao.findNameAndPhoneByUid(suretyId));

                    return new Result("所选担保人符合要求", "0", map);
                }
            }
        }
        return new Result("所选担保人不符合要求,请重新选择", "1");
    }

    @Override
    public Result orderReceive(UserOrder userOrder) {
        Integer userOrderId = userOrder.getId();
        User driver = userDao.selectByPrimaryKey(userOrder.getDriverUid());
        if (driver == null){
            return new Result("该用户不存在", "1");
        }
        String driverRole = driver.getRole();
        if (TRANSPORT_DRIVER.equals(driverRole) || PRIVATE_DRIVER.equals(driverRole) ){
            UserOrder realUserOrder = userOrderDao.selectByPrimaryKey(userOrderId);
            if (realUserOrder.getDriverUid() == null){
                //更新用户订单邀请
                invitationDao.updateStatusByUserOrderId(userOrderId);
                //增加userOrder的driverUid
                int count = userOrderDao.updateByPrimaryKeySelective(userOrder);
                if (count > 0){
                    return new Result("接单成功", "0");
                }else {
                    return new Result("接单失败", "1");
                }
            }else {
                return new Result("该用户订单已被其他司机接受", "1");
            }
        }else {
            return new Result("您没有接单资格", "1");
        }
    }

    @Override
    public Result suretyRefuse(Integer suretyId, Integer userOrderId) {
        User surety = userDao.selectByPrimaryKey(suretyId);
        UserOrder userOrder = userOrderDao.selectByPrimaryKey(userOrderId);
        if (WAIT_SURETY.equals(userOrder.getStatus())){
            if (surety.getId().equals(userOrder.getSuretyId())){
                Timestamp updateTime = new Timestamp(System.currentTimeMillis());
                int count = userOrderDao.updateStatusByType(userOrderId, updateTime, "suretyRefuse");
                if (count > 0){
                    return new Result("拒绝成功", "0");
                }else {
                    return new Result("拒绝失败", "1");
                }
            }else {
                return new Result("您不是本用户订单的担保人，拒绝失败", "1");
            }
        }else {
            return new Result("该用户订单已经不能被担保人操作","1");
        }

    }

    @Override
    public Result suretyConfirm(Integer suretyId, Integer userOrderId) {
        User surety = userDao.selectByPrimaryKey(suretyId);
        UserOrder userOrder = userOrderDao.selectByPrimaryKey(userOrderId);
        if (WAIT_SURETY.equals(userOrder.getStatus())){
            if (surety.getId().equals(userOrder.getSuretyId())){
                Timestamp updateTime = new Timestamp(System.currentTimeMillis());
                int count = userOrderDao.updateStatusByType(userOrderId, updateTime, "suretyConfirm");
                if (count > 0){
                    return new Result("确认成功", "0");
                }else {
                    return new Result("确认失败", "1");
                }
            }else {
                return new Result("您不是本用户订单的担保人，确认失败", "1");
            }
        }else {
            return new Result("该用户订单已不能被担保人操作","1");
        }

    }

    @Override
    public Result findAllByUid(Integer userId, Integer pageNumber) {
        PageHelper.startPage(pageNumber, PAGE_SIZE);
        List<UserOrder> userOrders = userOrderDao.findAllByUid(userId);
        return new Result("查找成功", "0", userOrders);
    }



    @Override
    public Result findNeedSurety(Integer suretyId, Integer pageNumber) {
        PageHelper.startPage(pageNumber, PAGE_SIZE);
        List<UserOrder> userOrders = userOrderDao.findBySuretyIdAndStatus(suretyId);
        return new Result("查找成功", "0", userOrders);
    }

    @Override
    public Result findWaitDeliver(Integer cid, Integer pageNumber) {
        PageHelper.startPage(pageNumber, PAGE_SIZE);
        List<UserOrder> userOrders = userOrderDao.findByCid(cid);
        return new Result("查找成功", "0", userOrders);
    }

    @Override
    public Result contactConfirm(Integer cid, Integer userOrderId) {
        User contact = userDao.selectByPrimaryKey(cid);
        UserOrder userOrder = userOrderDao.selectByPrimaryKey(userOrderId);
        Area consigneeArea = areaDao.selectByPrimaryKey(userOrder.getConsigneeAreaId());
        if (WAIT_CONTACT.equals(userOrder.getStatus())){
            if (contact.getId().equals(consigneeArea.getCid())){
                Timestamp updateTime = new Timestamp(System.currentTimeMillis());
                int count = userOrderDao.updateStatusByType(userOrderId, updateTime, "contactConfirm");
                if (count > 0){
                    return new Result("确认收货成功", "0");
                }else {
                    return new Result("确认收货失败", "1");
                }
            }else {
                return new Result("您不是本用户订单的收货人，确认收货失败", "1");
            }
        }else {
            return new Result("该用户订单不能被收货人操作","1");
        }
    }


    @Override
    public Result findNearByArea(String city, String district, String town, String village, Integer pageNumber) {
        PageHelper.startPage(pageNumber, PAGE_SIZE);
        List<UserOrder> userOrders = userOrderDao.findNearByArea(city, district, town, village);
        if (userOrders.size() > 0) {
            return new Result("查询成功", "0", userOrders);
        } else {
            return new Result("附近没有用户订单", "1", null);
        }
    }

    @Override
    public Result driverAccept(UserOrder userOrder) {
        Integer userOrderId = userOrder.getId();
        User driver = userDao.selectByPrimaryKey(userOrder.getDriverUid());
        if (driver == null){
            return new Result("该用户不存在", "1");
        }
        String driverRole = driver.getRole();
        if (TRANSPORT_DRIVER.equals(driverRole) || PRIVATE_DRIVER.equals(driverRole) ){
            UserOrder realUserOrder = userOrderDao.selectByPrimaryKey(userOrderId);
                if (realUserOrder.getDriverUid() == null){
                    //更新用户订单邀请
                    invitationDao.updateStatusByUserOrderId(userOrderId);
                    invitationDao.updateStatusByUserOrderIdAndDriverRouteId(userOrder.getDriverRouteId(), userOrderId);
                    //增加userOrder的driverUid
                    int count = userOrderDao.updateByPrimaryKeySelective(userOrder);
                    if (count > 0){
                        return new Result("接单成功", "0");
                    }else {
                        return new Result("接单失败", "1");
                    }
                }else {
                    return new Result("该用户订单已被其他司机接受", "1");
                }
        }else {
            return new Result("您没有接单资格", "1");
        }
    }

}
