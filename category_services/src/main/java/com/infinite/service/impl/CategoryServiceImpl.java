package com.infinite.service.impl;

import com.infinite.modal.Category;
import com.infinite.payload.dto.SalonDTO;
import com.infinite.repository.CategoryRepository;
import com.infinite.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category, SalonDTO salonDTO) {
        Category newCategory=new Category();
        newCategory.setName(category.getName());
        newCategory.setSalonId(salonDTO.getId());
        newCategory.setImage(category.getImage());
        return categoryRepository.save(newCategory);
    }

    @Override
    public Set<Category> getAllCategoriesBySalon(Long id) {
        return categoryRepository.findBySalonId(id);
    }

    @Override
    public Category getCategoryById(Long id) throws Exception {
        Category category=categoryRepository.findById(id).orElse(null);

        if(category==null){
            throw new Exception("Category does not exist with id"+id);
        }
        return category;
    }

    @Override
    public void deleteCategoryById(Long id,Long salonId) throws Exception {
        Category category=getCategoryById(id);
        if(!category.getSalonId().equals(salonId)){
            throw new Exception("you don't have permission to delete this category");
        }
        categoryRepository.deleteById(id);
    }
}
