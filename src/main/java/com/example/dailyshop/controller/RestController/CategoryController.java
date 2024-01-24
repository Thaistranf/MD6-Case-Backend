package com.example.dailyshop.controller.RestController;

import com.example.dailyshop.model.entity.Category;
import com.example.dailyshop.model.entity.Product;
import com.example.dailyshop.service.webservice.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAllCategory")
    //lấy ra list loại sản phẩm
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categories = categoryService.findAll();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
    }

    @PostMapping("/createCategory")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category categoryNew = categoryService.save(category);
        return new ResponseEntity<>(categoryNew, HttpStatus.OK);
    }

    @PutMapping("/editCategory/{id}")
    public ResponseEntity<Category> editCategory(@PathVariable Long id, @RequestBody Category category) {
        category.setId(id);
        Category categoryEdit = categoryService.save(category);
        return new ResponseEntity<>(categoryEdit, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<Category> removeCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findByIdCategory/{id}")
    public ResponseEntity<Optional<Category>> findByIdCategory(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
