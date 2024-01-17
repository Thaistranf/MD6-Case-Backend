package com.example.dailyshop.controller.RestController;
import com.example.dailyshop.model.entity.Product;
import com.example.dailyshop.service.webservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductService productService;
    private final Sort SORT_BY_TIME_DESC = Sort.by(Sort.Direction.DESC, "createAt");


    @GetMapping("/suppliers/getProductByAccountId/{id}")
    //lấy ra toàn bộ sản phẩm theo tài khoản có quyền nhà cung cấp
    public ResponseEntity<List<Product>> findProductBySupplier(@PathVariable Long id) {
        List<Product> productsList = productService.findProductByAccountIdAndIsDeleted(id,false,SORT_BY_TIME_DESC);
        if (productsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productsList, HttpStatus.OK);
        }
    }


    @GetMapping("/customer/getAllProduct")
    //lấy ra list danh sách sản phẩm
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productService.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }

    @PostMapping("/suppliers/createProduct")
    //thêm mới một sản phẩm
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product productNew = productService.save(product);
        return new ResponseEntity<>(productNew, HttpStatus.OK);
    }

    @PutMapping("/suppliers/editProduct/{id}")
    //Sửa thông tin sản phẩm
    public ResponseEntity<Product> editProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setProductID(id);
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/suppliers/deleteProduct/{id}")
    //Xóa một sản phẩm
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        product.get().setDeleted(true);
        productService.save(product.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/account/searchProduct")
    //Tìm kiếm sản phẩm theo tên gần đúng.
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String name) {
        List<Product> listProduct = productService.findProductByproductNameContaining(name);
        if (listProduct.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listProduct, HttpStatus.OK);
        }
    }

    @GetMapping("/searchProduct")
    //Tìm kiếm sản phẩm theo tên gần đúng.
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String name,@RequestParam String category,@RequestParam int minPrice,@RequestParam int maxPrice) {
        List<Product> listProduct = productService.searchProducts(name,category,minPrice,maxPrice);
        if (listProduct.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listProduct, HttpStatus.OK);
        }
    }


    @GetMapping("/account/view/{id}")
    //Tìm kiếm thông tin một sản phẩm
    public ResponseEntity<Optional<Product>> viewProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/suppliers/findProductById/{id}")
    public ResponseEntity<Optional<Product>> findProductById(@PathVariable Long id){
       Optional<Product> product = productService.findById(id);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @GetMapping("/getAllProduct")
    //lấy ra list danh sách sản phẩm
    public ResponseEntity<List<Product>> findAllWithoutLogin() {
        List<Product> products = productService.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }

    @GetMapping("/getProductTop")
    public ResponseEntity<List<Product>> getProductTop(){
        List<Product> products = productService.findTop5Products();
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(products,HttpStatus.OK);
        }
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
}
