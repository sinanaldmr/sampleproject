package com.sampleproject.service.impl;

import com.sampleproject.model.Product;
import com.sampleproject.repository.ProductRepository;
import com.sampleproject.repository.impl.ProductRepositoryImpl;
import com.sampleproject.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository=new ProductRepositoryImpl();

    @Override
    public Product saveProduct(Product product) {
        return productRepository.saveProduct(product);
    }

    @Override
    public boolean saveBatchProduct(List<Product> products) {
        return productRepository.saveBatchProduct(products);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }

    @Override
    public boolean removeProduct(int id) {
        return productRepository.removeProduct(id);
    }

    @Override
    public Product findProductById(int id) {
        return productRepository.findProductById(id);
    }

    @Override
    public List<Product> findProducts() {
        return productRepository.findProducts();
    }
}
