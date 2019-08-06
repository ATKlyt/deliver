package cn.deliver.service.impl;

import cn.deliver.dao.AreaDao;
import cn.deliver.dao.DriverOrderDao;
import cn.deliver.dao.UserInfoDao;
import cn.deliver.domain.Area;
import cn.deliver.domain.AreaList;
import cn.deliver.domain.DriverOrder;
import cn.deliver.domain.DriverOrderMessage;
import cn.deliver.service.DriverOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class DriverOrderServiceImpl implements DriverOrderService {
    @Autowired
    DriverOrderDao driverOrderDao;
    @Autowired
    UserInfoDao userInfoDao;
    @Autowired
    AreaDao areaDao;






    @Override
    public List<DriverOrderMessage> findNearbyDriverOrder(Integer uid) {
        HashMap<String, Object> map = new HashMap<>(16);
        //获取用户所属地区-----district：区
        Area userBelongArea = areaDao.findBelongAreaByUid(uid);
        String district = userBelongArea.getDistrict();
        return driverOrderDao.findNearbyDriverOrder(district);
    }

    @Override
    public int addDriverOrder(AreaList areas, DriverOrder driverOrder) {
        //出发地址
        String originalStatus = "4";
        Integer originalId = 0;
        Integer destinationId = 0;
        for (Area area : areas.getAreas()) {
            areaDao.insertSelective(area);
            if (originalStatus.equals(area.getStatus())){
                originalId = area.getId();
            }else {
                destinationId = area.getId();
            }
        }
        driverOrder.setOriginalId(originalId);
        driverOrder.setDestinationId(destinationId);
        driverOrder.setStatus("1");
        driverOrderDao.insertSelective(driverOrder);
        return 1;
    }
}
