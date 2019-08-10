package cn.deliver.service;

import cn.deliver.domain.Result;

public interface AddressService {
    Result getProvince();

    Result getAddress(Integer addressId);
}
