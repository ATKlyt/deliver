package cn.deliver.service.impl;

import cn.deliver.dao.AreaDao;
import cn.deliver.dao.DriverInfoDao;
import cn.deliver.dao.OrderDao;
import cn.deliver.dao.UserOrderDao;
import cn.deliver.domain.*;
import cn.deliver.service.OrderService;
import cn.deliver.utils.TimeUtil;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    UserOrderDao userOrderDao;
    @Autowired
    DriverInfoDao driverInfoDao;
    @Autowired
    AreaDao areaDao;
    //判断订单状态的锁对象
    private final Object orderObject = new Object();
    //判断用户订单数量的锁对象
    private final Object driverObject = new Object();

    private final String WAITDRIVERSTATUS = "1";
    private final Integer MAXNUMBER = 5;


    @Override
    public Result addOrderSelective(Order order) {
        if (orderDao.insertSelective(order) > 0) {
            return new Result("邀请成功，等待司机回应", "0");
        } else {
            return new Result("邀请失败", "1");
        }
    }

    @Override
    public Result findWaitOrder(Integer driverUid) {
        //获取该司机所有等待状态的order（等待司机接受,如果被其他司机接受了，则不再显示）且不过期
        //等待司机接受邀请---->status = "1"
        //邀请不过期----->现在的时间不超过更新时间2小时
        List<Order> orders = orderDao.findWaitOrder(driverUid);
        return new Result("查询成功", "0", orders);
    }


    @Transactional
    @Override
    public Result validateVillage(Integer orderId) {
        Order order = orderDao.selectByPrimaryKey(orderId);
        //司机所在村
        String driverVillage = areaDao.selectByPrimaryKey(order.getDriverUid()).getVillage();
        //收货人所在村
        String consigneeVillage = areaDao.selectByPrimaryKey
                (userOrderDao.selectByPrimaryKey(order.getUserOrderId()).getConsigneeAreaId()).getVillage();
        //发货人所在村
        String deliverVillage = areaDao.selectByPrimaryKey
                (userOrderDao.selectByPrimaryKey(order.getUserOrderId()).getId()).getVillage();

        if (driverVillage.equals(consigneeVillage) || driverVillage.equals(deliverVillage)) {
            return new Result("需要担保人", "0");
        } else {
            return new Result("不需要担保人", "0");
        }
    }

    @Transactional
    @Override
    public Result driverConfirmOrder(Integer orderId, Integer suretyId) {

        Order order = null;
        //判断订单状态是否等于1,即是否是等待司机确认的状态
        synchronized (orderObject) {
            order = orderDao.selectByPrimaryKey(orderId);
            if (!WAITDRIVERSTATUS.equals(order.getStatus())) {
                return new Result("该用户订单已被接受", "1");
            }
        }

        UserOrder userOrder = userOrderDao.selectByPrimaryKey(order.getUserOrderId());
        //若是不需要担保人,suretyId为null
        if (suretyId != null) {
            //将该用户订单标记为2,即该用户订单已被司机接单，等待担保人确认
            userOrder.setStatus("2");
            //跟新userOrder的status
            userOrderDao.updateByPrimaryKeySelective(userOrder);
            //封装担保人Id
            order.setSuretyId(suretyId);
            //将该订单标记为2,即该订单已被司机接单，等待担保人确认
            order.setStatus("3");
            order.setUpdateTime(TimeUtil.getNowTime());
            synchronized (orderObject) {
                //检查这个司机当前订单数量
                DriverInfo driverInfo = driverInfoDao.selectByPrimaryKey(order.getDriverUid());
                int orderNumber = driverInfo.getOrderNumber();
                if (orderNumber < MAXNUMBER) {
                    //更新此时司机订单数量----> +1
                    driverInfo.setOrderNumber(orderNumber + 1);
                    driverInfoDao.updateByPrimaryKey(driverInfo);
                    //返回
                    if (orderDao.updateByPrimaryKeySelective(order) > 0) {
                        return new Result("确认成功,等待担保人确认", "0");
                    }
                }else{
                    return new Result("当前司机接单数量为5", "1");
                }
            }
        } else {
            //将该用户订单标记为1,即该用户订单已被司机接单，交易达成
            userOrder.setStatus("1");
            //跟新userOrder的status
            userOrderDao.updateByPrimaryKeySelective(userOrder);
            //封装担保人Id
            order.setSuretyId(suretyId);
            //将该订单标记为3,即该订单已被司机接单，等待担保人确认
            order.setStatus("3");
            order.setUpdateTime(TimeUtil.getNowTime());
            synchronized (orderObject) {
                //检查这个司机当前订单数量
                DriverInfo driverInfo = driverInfoDao.selectByPrimaryKey(order.getDriverUid());
                int orderNumber = driverInfo.getOrderNumber();
                if (orderNumber < MAXNUMBER) {
                    //更新此时司机订单数量
                    driverInfo.setOrderNumber(orderNumber + 1);
                    driverInfoDao.updateByPrimaryKey(driverInfo);
                    //返回
                    if (orderDao.updateByPrimaryKeySelective(order) > 0) {
                        return new Result("确认成功", "0");
                    }
                }else{
                    return new Result("当前司机接单数量为5", "1");
                }
            }
        }

        return new Result("确认失败", "1");

    }

}
