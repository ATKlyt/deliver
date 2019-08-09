package cn.deliver.service.impl;

import cn.deliver.dao.AddressDao;
import cn.deliver.domain.Address;
import cn.deliver.domain.Result;
import cn.deliver.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressDao addressDao;

    @Override
    public Result getProvince() {
        List<Address> provinces =  addressDao.selectByPid(0);
        return new Result("查询成功", "0", provinces);
    }

    @Override
    public Result getAddress(Integer addressId) {
        List<Address> addressList =  addressDao.selectByPid(addressId);
        return new Result("查询成功", "0", addressList);
    }

}
