package com.example.dailyshop.service.webservice;

import com.example.dailyshop.model.entity.Product;
import com.example.dailyshop.repository.data.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    private final Sort SORT_BY_TIME_DESC = Sort.by(Sort.Direction.DESC, "createAt");


    @Override
    public List<Product> findAll() {
        return productRepository.findAllByIsDeleted(false, SORT_BY_TIME_DESC);
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
    public List<Product> findProductByAccountIdAndIsDeleted(Long id, boolean deleted, Sort sort) {
        return productRepository.findProductByAccountIdAndIsDeleted(id, deleted, SORT_BY_TIME_DESC);
    }

    @Override
    public Page<Product> findAllByIsDeleted(boolean deleted, Pageable pageable) {
        return productRepository.findAllByIsDeleted(false, pageable);
    }

    @Override
    public List<Product> findByProductNameContaining(String name) {
        return productRepository.findByProductNameContaining(name);
    }

    @Override
    public List<Product> findProductsByProductNameContaining(String name) {
        return productRepository.findProductsByProductNameContaining(name);
    }


    @Override
    public List<Product> findTop5Products() {
        return productRepository.findTop5Products();
    }

    public List<Product> findProductsByName(String name) {
        return productRepository.findProductsByProductNameContaining(name);
    }

    @Override
    public List<Product> findAllByCategoryId(Long id) {
        return productRepository.findAllByCategoryId(id);
    }
}
