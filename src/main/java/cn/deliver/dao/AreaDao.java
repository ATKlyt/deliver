package cn.deliver.dao;

import cn.deliver.domain.Area;
import cn.deliver.domain.AreaExample;
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

    int updateBelongAreaByUid(Area area);

    int updateDefaultById(Integer areaId);

    int updateNonDefaultByUid(Integer uid);

    List<Area> findAllConsigneeByUid(Integer uid);

    Area findDefaultByUid(Integer uid);

    Area findBelongAreaByUid(Integer uid);

    //==================================俊彬=====================================

    int insertUserArea(Area area);
}