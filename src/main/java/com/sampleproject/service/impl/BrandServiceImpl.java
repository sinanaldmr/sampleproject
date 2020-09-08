package com.sampleproject.service.impl;

import com.sampleproject.model.Brand;
import com.sampleproject.repository.BrandRepository;
import com.sampleproject.repository.impl.BrandRepositoryImpl;
import com.sampleproject.service.BrandService;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    private BrandRepository brandRepository=new BrandRepositoryImpl();

    @Override
    public Brand findBrandById(int id) {
        return brandRepository.findBrandById(id);
    }

    @Override
    public List<Brand> findBrans() {
        return brandRepository.findBrans();
    }
}
