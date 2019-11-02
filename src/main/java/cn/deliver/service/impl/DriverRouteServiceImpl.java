package cn.deliver.service.impl;

import cn.deliver.dao.*;
import cn.deliver.domain.*;
import cn.deliver.service.DriverRouteService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverRouteServiceImpl implements DriverRouteService {

    @Autowired
    DriverRouteDao driverRouteDao;
    @Autowired
    UserInfoDao userInfoDao;
    @Autowired
    AreaDao areaDao;
    @Autowired
    UserOrderDao userOrderDao;
    @Autowired
    UserDao userDao;
    @Autowired
    InvitationDao invitationDao;

    /**
     * 分页,每一页有5条数据
     */
    private final Integer PAGE_SIZE = 5;
    /**
     * 司机行程尚未过期
     */
    private static final String NON_OVERDUE = "0";

    /**
     * 身份:客运车司机
     */
    private static final String TRANSPORT_DRIVER = "3";
    /**
     * 身份：私家车司机
     */
    private static final String PRIVATE_DRIVER = "2";

    @Override
    public Result addDriverRoute(DriverRoute driverRoute,
                                 Area originalArea, Area destinationArea) {
        User user = userDao.selectByPrimaryKey(driverRoute.getUid());
        String userRole = user.getRole();
        if (TRANSPORT_DRIVER.equals(userRole) || PRIVATE_DRIVER.equals(userRole)){
            //将出发/目的地址添加进数据库，并获取地址信息的id
            areaDao.insertSelective(originalArea);
            areaDao.insertSelective(destinationArea);
            driverRoute.setOriginalAreaId(originalArea.getId());
            driverRoute.setDestinationAreaId(destinationArea.getId());
            driverRouteDao.insertSelective(driverRoute);
            return new Result("发布成功","0");
        }else {
            return new Result("该用户不是司机，发布行程失败", "1");
        }
    }

    @Override
    public Result cancelDriverRoute(Integer userId, DriverRoute driverRoute) {
        DriverRoute realDriverRoute = driverRouteDao.selectByPrimaryKey(driverRoute.getId());
        if ( !realDriverRoute.getUid().equals(userId)){
            return new Result("无权操作该司机行程", "1");
        }
        invitationDao.updateStatusByDriverRouteId(driverRoute.getId());
        int count = driverRouteDao.updateByPrimaryKeySelective(driverRoute);
        if (count > 0){
            return new Result("取消成功", "0");
        }else {
            return new Result("取消失败", "1");
        }
        //状态：等待，取消，接受，过期，进行消息推送
        //邀请了也能被主动接单
        //邀请/删除/取消验证是否过期

    }

    @Override
    public Result deleteDriverRoute(Integer userId, DriverRoute driverRoute) {
        Integer driverRouteId = driverRoute.getId();
        DriverRoute realDriverRoute = driverRouteDao.selectByPrimaryKey(driverRouteId);
        if ( !realDriverRoute.getUid().equals(userId)){
            return new Result("无权操作该司机行程", "1");
        }
        String status = realDriverRoute.getStatus();
        if ( !NON_OVERDUE.equals(status)){
            return new Result("删除失败，只有过期或已取消的行程可被删除", "1");
        }
        driverRouteDao.updateByPrimaryKeySelective(driverRoute);
        return new Result("删除成功", "0");
    }

    @Override
    public Result findNearByUserOrderId(Integer userOrderId, Integer pageNumber) {
        UserOrder userOrder = userOrderDao.selectByPrimaryKey(userOrderId);
        Area deliverArea = areaDao.selectByPrimaryKey(userOrder.getDeliverAreaId());
        String city = deliverArea.getCity();
        String district = deliverArea.getDistrict();
        String town = deliverArea.getTown();
        String village = deliverArea.getVillage();
        PageHelper.startPage(pageNumber, PAGE_SIZE);
        List<DriverRouteRelated> driverRouteRelated = driverRouteDao.findNearByArea(city, district, town, village);
        if (driverRouteRelated.size() > 0 ){
            return new Result("查找成功", "0", driverRouteRelated);
        }else{
            return new Result("没有顺路的司机", "0", null);
        }
    }

    @Override
    public Result findDetailByDriverRouteId(Integer driverRouteId) {
        DriverRouteRelated driverRouteRelated = driverRouteDao.findDetailById(driverRouteId);
        return new Result("查询成功", "0", driverRouteRelated);
    }


}
