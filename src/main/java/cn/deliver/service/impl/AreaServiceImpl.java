package cn.deliver.service.impl;

import cn.deliver.dao.AreaDao;
import cn.deliver.domain.Area;
import cn.deliver.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    AreaDao areaDao;


    @Override
    public int updateBelongAreaByUid(Area area) {
        return areaDao.updateBelongAreaByUid(area);
    }

    @Override
    public int insertSelective(Area area) {
        return areaDao.insertSelective(area);
    }

    @Override
    public int updateConsignee(Integer areaId, Integer uid) {
        int status = 1;
        //查询是否有默认收货地址
        if (areaDao.findDefaultByUid(uid) != null){
            //有则将原本的默认收货地址修改成非默认
            status = areaDao.updateNonDefaultByUid(uid);
        }
        //上一步修改成功或者没有上一步
        if (status > 0 ){
            status = areaDao.updateDefaultById(areaId);
        }
        return status;
    }

    @Override
    public List<Area> findAllConsigneeByUid(Integer uid) {
        return areaDao.findAllConsigneeByUid(uid);
    }

    @Override
    public int deleteConsignee(Integer areaId) {
        return areaDao.deleteByPrimaryKey(areaId);
    }


}
