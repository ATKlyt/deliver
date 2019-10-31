package cn.deliver.service;

import cn.deliver.domain.DriverRoute;
import cn.deliver.domain.Invitation;
import cn.deliver.domain.Result;

public interface InvitationService {
    Result inviteDriver(Invitation invitation);

    Result findDriversInvitation(Integer driverUid);

    Result findInvitationByUserOrderId(Integer uid);

    DriverRoute findInvitationRoute(Integer userOrderId, Integer driverRouteId);
}
