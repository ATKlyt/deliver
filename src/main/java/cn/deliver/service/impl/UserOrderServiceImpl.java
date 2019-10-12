package cn.deliver.service.impl;

import cn.deliver.dao.AreaDao;
import cn.deliver.dao.UserDao;
import cn.deliver.dao.UserInfoDao;
import cn.deliver.dao.UserOrderDao;
import cn.deliver.domain.*;
import cn.deliver.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private final String TRANSPORT_DRIVER = "客运车司机";
    private final String PRIVATE_DRIVER = "私家车司机";
    private final String COMMON_USER = "用户";

    @Override
    public Result addUserOrder(UserOrder userOrder) {
        User user = userDao.selectByPrimaryKey(userOrder.getUid());
        String userRole = user.getRole();
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
    public Result findNearByVillage(String village) {
        List<UserOrderMessage> userOrderMessages = userOrderDao.findNearByVillage(village);
        if (userOrderMessages.size() > 0) {
            return new Result("查询成功", "0", userOrderMessages);
        } else {
            return new Result("附近没有用户订单", "1", null);
        }
    }

    @Override
    public Result findDetailByUserOrderId(Integer userOrderId) {
        Map<String, Object> map = new HashMap<>(16);
        UserOrder userOrder = userOrderDao.selectByPrimaryKey(userOrderId);
        if (userOrder != null) {
            Area area = areaDao.selectByPrimaryKey(userOrder.getConsigneeAreaId());
            String phone = userDao.findPhoneByUid(area.getCid());
            String name = userInfoDao.findNameByUid(area.getCid());
            map.put("consigneePhone", phone);
            map.put("consigneeName", name);
            map.put("userOrder", userOrder);
            map.put("consigneeArea", area);
            return new Result("查询成功", "0", map);
        } else {
            return new Result("没有该用户订单哦", "1", null);
        }
    }
}
