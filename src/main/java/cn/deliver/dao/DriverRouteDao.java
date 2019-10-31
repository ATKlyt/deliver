package cn.deliver.dao;

import cn.deliver.domain.DriverRoute;
import cn.deliver.domain.DriverRouteRelated;
import cn.deliver.domain.InvitationBrief;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DriverRouteDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DriverRoute record);

    int insertSelective(DriverRoute record);

    DriverRoute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DriverRoute record);

    int updateByPrimaryKey(DriverRoute record);

    List<DriverRouteRelated> findNearByArea(@Param("city") String city,
                                            @Param("district") String district,
                                            @Param("town") String town,
                                            @Param("village") String village);

    DriverRouteRelated findDetailById(Integer driverRouteId);


    List<InvitationBrief> findInvitationByUserOrderId(Integer userOrderId);

    DriverRoute findInvitationByDriverRouteIdAndUserOrderId(@Param("userOrderId") Integer userOrderId, @Param("driverRouteId") Integer driverRouteId);
}