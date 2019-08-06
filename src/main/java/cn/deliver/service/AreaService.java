package cn.deliver.service;




import cn.deliver.domain.Area;

import java.util.List;

public interface AreaService {

    int updateBelongAreaByUid(Area area);

    int insertSelective(Area area);

    int updateConsignee(Integer areaId, Integer uid);

    List<Area> findAllConsigneeByUid(Integer uid);

    int deleteConsignee(Integer areaId);
}
