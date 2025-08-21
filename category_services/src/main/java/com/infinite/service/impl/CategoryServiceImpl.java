package com.infinite.service.impl;

import com.infinite.modal.Category;
import com.infinite.payload.dto.SalonDTO;
import com.infinite.service.CategoryService;

import java.util.Set;

public class CategoryServiceImpl implements CategoryService {


    @Override
    public Category saveCategory(Category category, SalonDTO salonDTO) {
        return null;
    }

    @Override
    public Set<Category> getAllCategoriesBySalon(Long id) {
        return Set.of();
    }

    @Override
    public Category getCategoryById(Long id) {
        return null;
    }

    @Override
    public void deleteCategoryById(Long id) {

    }
}
