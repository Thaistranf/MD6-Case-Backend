package com.example.dailyshop.service.webservice;

import com.example.dailyshop.model.entity.Product;
import com.example.dailyshop.repository.data.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> findAll() {
        return productRepository.findAllByIsDeleted(false);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long productID) {
        productRepository.deleteById(productID);
    }

    @Override
    public Optional<Product> findById(Long productID) {
        return productRepository.findById(productID);
    }

    @Override
    public List<Product> findProductByproductNameContaining(String name) {
        return productRepository.findProductByproductNameContaining(name);
    }

    @Override
    public List<Product> findProductByAccountIdAndIsDeleted(Long id, boolean deleted) {
        return productRepository.findProductByAccountIdAndIsDeleted(id,deleted);
    }
}
