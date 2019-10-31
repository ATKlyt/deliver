package cn.deliver.dao;

import cn.deliver.domain.Area;
import cn.deliver.domain.ConsigneeDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AreaDao {

    int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

    /**
     * 更新所属地区
     * @param area
     * @return
     */
    int updateBelongAreaByUid(Area area);

    /**
     *   将非默认收货地址修改为默认收货地址
     * 或将非默认发货地址修改为默认发货地址
     * @param areaId
     * @param AreaType consignee/deliver ---->  收货地址/发货地址
     * @return
     */
    int updateCommonToDefaultById(@Param("areaId") Integer areaId, @Param("AreaType") String AreaType);

    /**
     * 将默认收货地址修改为非默认收货地址
     * 或将默认发货地址修改为非默认发货地址
     * @param uid
     * @param AreaType consignee/deliver ---->  收货地址/发货地址
     * @return
     */
    int updateDefaultToCommonByUid(@Param("uid") Integer uid, @Param("AreaType") String AreaType);


    /**
     * 通过uid和类型查找地址
     * 用于：查找默认收货地址（uid , 2）
     *      查找默认发货地址（uid , 4）
     *      查找所属地址   （uid , 1）
     *      查找注册地址   （uid , 0）
     * @param uid 用户id
     * @param type 地址状态
     * @return
     */
    Area findAreaByUidAndType(@Param("uid") Integer uid, @Param("type") String type);

    int insertUserArea(Area area);

    List<Area> findAllDeliverAreaByUid(Integer uid);

    List<ConsigneeDetail> findAllConsigneeAreaByUid(Integer uid);

    int deleteByAreaId(Integer areaId);


}