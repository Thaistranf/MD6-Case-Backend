package com.example.dailyshop.service;

import com.example.dailyshop.model.address.District;
import com.example.dailyshop.model.address.Province;
import com.example.dailyshop.model.address.Ward;

import java.util.List;

public interface AddressService {
    List<Province> getAllProvinces();
    List<District> getDistrictsByProvinceId(Long provinceId);
    List<Ward> getWardsByDistrictId(Long districtId);
}
