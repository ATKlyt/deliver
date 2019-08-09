package cn.deliver.service;




import cn.deliver.domain.Area;
import cn.deliver.domain.Result;

import java.util.List;

public interface AreaService {

    /**
     * 跟新所属地区
     * @param area
     * @return
     */
    Result updateBelongAreaByUid(Area area);

    Result insertSelective(Area area);

    /**
     * 修改用户默认收货/发货地址
     * @param areaId
     * @param uid
     * @param type consignee/deliver ---->  收货地址/发货地址
     * @return
     */
    Result updateArea(Integer areaId, Integer uid, String type);

    /**
     * 查询所有收货地址
     * @param uid
     * @return
     */
    Result findAllConsigneeByUid(Integer uid);

    Result deleteAreaByAreaId(Integer areaId);

    Result findAllDeliverByUid(Integer uid);
}
