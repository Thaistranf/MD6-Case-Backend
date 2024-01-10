package com.example.dailyshop.service.webservice;

import com.example.dailyshop.model.entity.Product;

import java.util.List;

public interface IProductService extends IGenerateService<Product> {
    List<Product> findProductByproductNameContaining(String name);

    List<Product> findProductByAccountId(Long id);

}
