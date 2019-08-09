package cn.deliver.service.impl;

import cn.deliver.dao.AreaDao;
import cn.deliver.domain.Area;
import cn.deliver.domain.ConsigneeDetail;
import cn.deliver.domain.Result;
import cn.deliver.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    AreaDao areaDao;

    @Override
    public Result updateBelongAreaByUid(Area area) {
        int rows =  areaDao.updateBelongAreaByUid(area);
        if (rows > 0){
            return new Result("修改成功", "0");
        }else{
            return new Result("修改失败", "1");
        }
    }

    @Override
    public Result insertSelective(Area area) {
        int rows = areaDao.insertSelective(area);
        if (rows > 0){
            return new Result("添加成功","0" , area.getId());
        }else{
            return new Result("添加失败","1");
        }
    }

    @Override
    public Result updateArea(Integer areaId, Integer uid ,String type) {
        //地址类型
        final String consignee = "consignee";
        final String consigneeStatus = "2";
        final String deliverStatus = "4";
        int status = 1;
        //地址类型为收货地址
        if (consignee.equals(type)){
            //判断该用户是否有默认地址
            if (areaDao.findAreaByUidAndStatus(uid,consigneeStatus) != null){
                //将原本的默认收货地址修改成非默认收货地址
                status = areaDao.updateNonDefaultByUid(uid,type);
            }
        }else{
            //判断该用户是否有默认发货地址
            if (areaDao.findAreaByUidAndStatus(uid,deliverStatus) != null){
                //将原本的默认发货地址修改成非默认发货地址
                status = areaDao.updateNonDefaultByUid(uid,type);
            }
        }

        //上一步修改成功或者没有这行上一步
        if (status > 0 ){
            status = areaDao.updateDefaultById(areaId,type);
        }
        if (status > 0){
            return new Result("修改成功", "0");
        }else{
            return new Result("修改失败", "1");
        }
    }

    @Override
    public Result findAllConsigneeByUid(Integer uid) {
        //查询所有收货地址
        List<ConsigneeDetail> consignees = areaDao.findAllConsigneesByUid(uid);
        return new Result("查询成功","0",consignees);
    }

    @Override
    public Result deleteAreaByAreaId(Integer areaId) {
        try {
            int status = areaDao.deleteByPrimaryKey(areaId);
            if (status > 0){
                return new Result("删除成功","0");
            }else{
                return new Result("删除失败","1");
            }
        }catch (Exception e){
            e.printStackTrace();
            int status = areaDao.deleteByAreaId(areaId);
            if (status > 0){
                return new Result("删除成功","0");
            }else{
                return new Result("删除失败","1");
            }
        }
    }

    @Override
    public Result findAllDeliverByUid(Integer uid) {
        //查询所有发货地址
        List<Area> delivers = areaDao.findAllDeliverByUid(uid);
        return new Result("查询成功","0",delivers);
    }


}
