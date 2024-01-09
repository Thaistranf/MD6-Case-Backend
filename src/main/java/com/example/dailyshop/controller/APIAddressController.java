package com.example.dailyshop.controller;

import com.example.dailyshop.model.address.District;
import com.example.dailyshop.model.address.Province;
import com.example.dailyshop.model.address.Ward;
import com.example.dailyshop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class APIAddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/provinces")
    public List<Province> getAllProvinces() {
        return addressService.getAllProvinces();
    }

    @GetMapping("/districts/{provinceId}")
    public List<District> getDistrictsByProvinceId(@PathVariable Long provinceId) {
        return addressService.getDistrictsByProvinceId(provinceId);
    }

    @GetMapping("/wards/{districtId}")
    public List<Ward> getWardsByDistrictId(@PathVariable Long districtId) {
        return addressService.getWardsByDistrictId(districtId);
    }
}
