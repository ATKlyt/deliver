package cn.deliver.service.impl;

import cn.deliver.dao.AreaDao;
import cn.deliver.domain.Area;
import cn.deliver.domain.ConsigneeDetail;
import cn.deliver.domain.Result;
import cn.deliver.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    AreaDao areaDao;

    @Override
    public Area selectByPrimaryKey(Integer areaId) {
        return areaDao.selectByPrimaryKey(areaId);
    }

    @Override
    public Result updateBelongAreaByUid(Area area) {
        int count =  areaDao.updateBelongAreaByUid(area);
        if (count > 0){
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
    public Result updateDefaultArea(Integer areaId, Integer uid ,String AreaType) {
        //地址类型
        String consigneeType = "consignee";
        String deliverType = "deliver";
        String defaultConsigneeAreaType = "2";
        String defaultDeliverAreaType = "4";
        int flag = 1;
        //地址类型为收货地址
        if (consigneeType.equals(AreaType)){
            //判断该用户是否有默认地址
            if (areaDao.findAreaByUidAndType(uid,defaultConsigneeAreaType) != null){
                //将原本的默认收货地址修改成非默认收货地址
                flag = areaDao.updateDefaultToCommonByUid(uid,AreaType);
            }
        }else if (deliverType.equals(AreaType)){
            //判断该用户是否有默认发货地址
            if (areaDao.findAreaByUidAndType(uid,defaultDeliverAreaType) != null){
                //将原本的默认发货地址修改成非默认发货地址
                flag = areaDao.updateDefaultToCommonByUid(uid,AreaType);
            }
        }
        //上一步修改成功或者没有这行上一步
        if (flag > 0 ){
            flag = areaDao.updateCommonToDefaultById(areaId,AreaType);
        }
        if (flag > 0){
            return new Result("修改成功", "0");
        }else{
            return new Result("修改失败", "1");
        }
    }

    @Override
    public Result findAllConsigneeAreaByUid(Integer uid) {
        //查询所有收货地址
        List<ConsigneeDetail> consignees = areaDao.findAllConsigneeAreaByUid(uid);
        return new Result("查询成功","0",consignees);
    }

    @Override
    public Result findAllDeliverAreaByUid(Integer uid) {
        //查询所有发货地址
        List<Area> delivers = areaDao.findAllDeliverAreaByUid(uid);
        return new Result("查询成功","0",delivers);
    }

    @Override
    public Result deleteAreaByAreaId(Integer areaId) {
        int count;
        try {
            count = areaDao.deleteByPrimaryKey(areaId);
            if (count > 0){
                return new Result("删除成功","0");
            }else{
                return new Result("删除失败","1");
            }
        }catch (Exception e){
            //当这条地址数据被引用，则将其设置为不可见
            e.printStackTrace();
            count = areaDao.deleteByAreaId(areaId);
            if (count > 0){
                return new Result("删除成功","0");
            }else{
                return new Result("删除失败","1");
            }
        }
    }

    @Override
    public Result updateByUserOrderIdSelective(Area area) {
        int count = areaDao.updateByPrimaryKeySelective(area);
        if (count > 0){
            return new Result("修改成功","0");
        }else{
            return new Result("修改失败","1");
        }
    }


}
