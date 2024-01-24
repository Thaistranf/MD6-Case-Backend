package com.example.dailyshop.controller.RestController;

import com.example.dailyshop.model.entity.Product;
import com.example.dailyshop.service.webservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        List<Product> productsList = productService.findProductByAccountIdAndIsDeleted(id, false, SORT_BY_TIME_DESC);
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
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String name, @RequestParam(required = false) Integer minPrice, @RequestParam(required = false) Integer maxPrice) {
        List<Product> listProduct;

        if (minPrice != null && maxPrice != null) {
            // Nếu cả minPrice và maxPrice được nhập, thực hiện tìm kiếm theo tất cả các điều kiện
            listProduct = productService.findProductsByConditions(name, minPrice, maxPrice);
        } else {
            // Nếu không có giá trị cho minPrice hoặc maxPrice, chỉ tìm kiếm theo tên sản phẩm
            listProduct = productService.findProductsByName(name);
        }

        if (listProduct.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listProduct, HttpStatus.OK);
        }
    }

    @GetMapping("/getProductById/{id}")
    //Tìm kiếm thông tin một sản phẩm
    public ResponseEntity<Optional<Product>> viewProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/suppliers/findProductById/{id}")
    //thừa code
    public ResponseEntity<Optional<Product>> findProductById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
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


    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> findAllByCategoryId(@PathVariable Long id) {
        List<Product> productListByCategoryId = productService.findAllByCategoryId(id);
        if (productListByCategoryId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productListByCategoryId, HttpStatus.OK);
        }
    }

    @GetMapping("/getProductTop")
    public ResponseEntity<List<Product>> getProductTop() {
        List<Product> products = productService.findTop5Products();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }

    @GetMapping("/getAllProductIsDeleted")
    //phân trang sản phẩm và ưu tiên hiển thị sản phẩm mới nhất theo thời gian.
    public ResponseEntity<Page<Product>> getAllProduct(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int size) {
        {
            Pageable pageable;
            Sort sort = Sort.by(Sort.Direction.DESC, "createAt");
            pageable = PageRequest.of(page, size, sort);
            Page<Product> products = productService.findAllByIsDeleted(false, pageable);
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(products, HttpStatus.OK);
            }
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/searchProductByName")
    //gia giam dan
    public ResponseEntity<List<Product>> searchProductByName(@RequestParam String name) {
        List<Product> listProduct = productService.findByProductNameContaining(name);
        if (listProduct.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listProduct, HttpStatus.OK);
        }
    }

    @GetMapping("/searchProductByNamePrice")
    //gia tang dan
    public ResponseEntity<List<Product>> searchProductByNamePrice(@RequestParam String name) {
        List<Product> listProduct = productService.findProductsByProductNameContaining(name);
        if (listProduct.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listProduct, HttpStatus.OK);
        }
    }


}
