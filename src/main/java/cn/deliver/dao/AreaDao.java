package cn.deliver.dao;

import cn.deliver.domain.Area;
import cn.deliver.domain.AreaExample;
import cn.deliver.domain.ConsigneeDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AreaDao {
    long countByExample(AreaExample example);

    int deleteByExample(AreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    List<Area> selectByExample(AreaExample example);

    Area selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Area record, @Param("example") AreaExample example);

    int updateByExample(@Param("record") Area record, @Param("example") AreaExample example);

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
     * @param type consignee/deliver ---->  收货地址/发货地址
     * @return
     */
    int updateDefaultById(@Param("areaId") Integer areaId, @Param("status") String type);

    /**
     * 将默认收货地址修改为非默认收货地址
     * 或将默认发货地址修改为非默认发货地址
     * @param uid
     * @param type consignee/deliver ---->  收货地址/发货地址
     * @return
     */
    int updateNonDefaultByUid(@Param("uid") Integer uid, @Param("status") String type);


    /**
     * 通过uid和状态查找地址
     * 用于：查找默认收货地址（uid , 2）
     *      查找默认发货地址（uid , 4）
     *      查找所属地址   （uid , 1）
     *      查找注册地址   （uid , 0）
     * @param uid 用户id
     * @param status 地址状态
     * @return
     */
    Area findAreaByUidAndStatus(@Param("uid") Integer uid, @Param("status") String status);


    //==================================俊彬=====================================

    int insertUserArea(Area area);

    List<Area> findAllDeliverByUid(Integer uid);

    List<ConsigneeDetail> findAllConsigneesByUid(Integer uid);

    int deleteByAreaId(Integer areaId);
}