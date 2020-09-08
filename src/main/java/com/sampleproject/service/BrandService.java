package com.sampleproject.service;

import com.sampleproject.model.Brand;

import java.util.List;

public interface BrandService {
    Brand findBrandById(int id);
    List<Brand> findBrans();
}
