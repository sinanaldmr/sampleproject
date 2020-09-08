package com.sampleproject.service.impl;

import com.sampleproject.model.Category;
import com.sampleproject.repository.CategoryRepository;
import com.sampleproject.repository.impl.CategoryRepositoryImpl;
import com.sampleproject.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository=new CategoryRepositoryImpl();

    @Override
    public Category findCategoryById(int id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    public List<Category> findCategories() {
        return categoryRepository.findCategories();
    }
}
