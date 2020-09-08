package com.sampleproject.service;

import com.sampleproject.model.Category;

import java.util.List;

public interface CategoryService {
    Category findCategoryById(int id);
    List<Category> findCategories();
}
