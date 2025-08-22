package com.infinite.controller;

import com.infinite.modal.Category;
import com.infinite.payload.dto.SalonDTO;
import com.infinite.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories/salon-owner")
public class SalonCategoryController {

    private final CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        SalonDTO salonDTO=new SalonDTO();
        salonDTO.setId(1L);
        Category savedCategory=categoryService.saveCategory(category,salonDTO);
        return ResponseEntity.ok(savedCategory);
    }
}
