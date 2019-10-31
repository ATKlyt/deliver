package cn.deliver.service;




import cn.deliver.domain.Area;
import cn.deliver.domain.Result;

public interface AreaService {


    Area selectByPrimaryKey(Integer areaId);

    /**
     * 更新所属地区
     * @param area
     * @return
     */
    Result updateBelongAreaByUid(Area area);

    /**
     * 添加一条地址信息
     * @param area
     * @return
     */
    Result insertSelective(Area area);

    /**
     * 修改用户默认收货/发货地址
     * @param areaId
     * @param uid
     * @param AreaType consignee/deliver ---->  收货地址/发货地址
     * @return
     */
    Result updateDefaultArea(Integer areaId, Integer uid, String AreaType);

    /**
     * 查询所有收货地址，包含收货人的姓名与电话
     * @param uid
     * @return
     */
    Result findAllConsigneeAreaByUid(Integer uid);

    /**
     * 查询所有发货地址
     * @param uid
     * @return
     */
    Result findAllDeliverAreaByUid(Integer uid);

    /**
     * 通过地址Id删除地址
     * @param areaId
     * @return
     */
    Result deleteAreaByAreaId(Integer areaId);


    Result updateByUserOrderIdSelective(Area area);
}
