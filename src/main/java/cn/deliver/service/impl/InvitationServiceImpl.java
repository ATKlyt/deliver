package cn.deliver.service.impl;

import cn.deliver.dao.*;
import cn.deliver.domain.*;
import cn.deliver.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    InvitationDao invitationDao;
    @Autowired
    DriverRouteDao driverRouteDao;
    @Autowired
    UserOrderDao userOrderDao;
    @Autowired
    AreaDao areaDao;
    @Autowired
    UserDao userDao;
    /**
     * 司机行程状态：等待用户邀请
     */
    private final String WAIT_USER_INVITE = "0";


    @Override
    public Result inviteDriver(Invitation invitation) {
        DriverRoute driverRoute = driverRouteDao.selectByPrimaryKey(invitation.getDriverRouteId());
        String driverRouteStatus = driverRoute.getStatus();
        //检查司机行程状态是否为等待用户邀请
        if (WAIT_USER_INVITE.equals(driverRouteStatus)){
            int count = invitationDao.insertSelective(invitation);
            if (count > 0){
                return new Result("邀请成功", "0");
            }else {
                return new Result("邀请失败", "1");
            }
        }else {
            return new Result("该司机行程已不可被邀请", "1");
        }
    }

    @Override
    public Result findDriversInvitation(Integer driverUid) {
        List<UserOrder> userOrders = userOrderDao.findInvitationByDriverUid(driverUid);
        return new Result("查找成功", "0", userOrders);
    }

    @Override
    public Result findInvitationByUserOrderId(Integer userOrderId) {
        List<InvitationBrief> invitationBriefLists = driverRouteDao.findInvitationByUserOrderId(userOrderId);
        return new Result("查找成功", "0", invitationBriefLists);
    }

    @Override
    public DriverRoute findInvitationRoute(Integer userOrderId, Integer driverRouteId) {
        DriverRoute driverRoute = driverRouteDao.findInvitationByDriverRouteIdAndUserOrderId(userOrderId, driverRouteId);
        return driverRoute;
    }


}
