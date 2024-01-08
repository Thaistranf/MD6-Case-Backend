package com.example.dailyshop.service.impl;

import com.example.dailyshop.model.address.District;
import com.example.dailyshop.model.address.Province;
import com.example.dailyshop.model.address.Ward;
import com.example.dailyshop.repository.addressRepo.DistrictRepository;
import com.example.dailyshop.repository.addressRepo.ProvinceRepository;
import com.example.dailyshop.repository.addressRepo.WardRepository;
import com.example.dailyshop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private WardRepository wardRepository;
    @Override
    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    @Override
    public List<District> getDistrictsByProvinceId(Long provinceId) {
        return districtRepository.findByProvinceId(provinceId);
    }

    @Override
    public List<Ward> getWardsByDistrictId(Long districtId) {
        return wardRepository.findByDistrictId(districtId);
    }
}
