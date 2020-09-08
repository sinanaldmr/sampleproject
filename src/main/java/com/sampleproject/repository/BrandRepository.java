package com.sampleproject.repository;

import com.sampleproject.model.Brand;

import java.util.List;

public interface BrandRepository {
    Brand findBrandById(int id);
    List<Brand> findBrans();
}
