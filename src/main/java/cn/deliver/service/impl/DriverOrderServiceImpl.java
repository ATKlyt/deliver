package cn.deliver.service.impl;

import cn.deliver.dao.*;
import cn.deliver.domain.*;
import cn.deliver.service.DriverOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverOrderServiceImpl implements DriverOrderService {
    @Autowired
    DriverOrderDao driverOrderDao;
    @Autowired
    UserInfoDao userInfoDao;
    @Autowired
    AreaDao areaDao;
    @Autowired
    UserOrderDao userOrderDao;
    @Autowired
    UserDao userDao;
    @Autowired
    OrderDao orderDao;
    private final String TRANSPORT_DRIVER = "客运车司机";
    private final String PRIVATE_DRIVER = "私家车司机";




    @Override
    public Result findNearByUserOrder(Integer userOrderId) {
        try {
            //查找该用户订单的具体信息
            UserOrder userOrder = userOrderDao.selectByPrimaryKey(userOrderId);
            //查找该用户订单的发货地址的具体信息
            Area deliverArea = areaDao.selectByPrimaryKey(userOrder.getDeliverAreaId());
            //找到用户订单发货地址：村
            String village = deliverArea.getVillage();
            List<DriverOrderMessage> nearbyDriverOrders = driverOrderDao.findNearbyDriverOrder(village);
            if (nearbyDriverOrders.size() > 0 ){
                return new Result("查找成功", "0", nearbyDriverOrders);
            }else{
                return new Result("该订单没有顺路的司机哦", "0", null);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
            return new Result("查找失败，该用户订单不存在", "0", null);
        }

    }

    @Override
    public Result addDriverOrder(Area originalArea, Area consigneeArea, DriverOrder driverOrder) throws InterruptedException {
        User user = userDao.selectByPrimaryKey(driverOrder.getUid());
        String userRole = user.getRole();
        if (TRANSPORT_DRIVER.equals(userRole) || PRIVATE_DRIVER.equals(userRole)){
            areaDao.insertSelective(originalArea);
            areaDao.insertSelective(consigneeArea);
            driverOrder.setOriginalId(originalArea.getId());
            driverOrder.setDestinationId(consigneeArea.getId());
            //该司机行程处于未过期状态
            driverOrder.setStatus("1");
            if (driverOrderDao.insertSelective(driverOrder)>0){
                return new Result("发布成功", "0");
            }else{
                return new Result("发布失败", "1");
            }
        }else {
            return new Result("该用户不是司机，发单失败", "1");
        }

    }


    /**
     * 查询司机行程详情
     * @param driverOrderId
     * @return
     */
    @Override
    public Result findDetailByDriverOrder(Integer driverOrderId) {
        DriverOrderMessage driverOrderMessage = driverOrderDao.findDetailByUserOrder(driverOrderId);
        return new Result("查询成功", "0", driverOrderMessage);
    }
}
