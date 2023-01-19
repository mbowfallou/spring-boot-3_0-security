package com.groupeisi.service;

import com.groupeisi.entity.ProductEntity;
import com.groupeisi.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private IProductRepository repository;
    public List<ProductEntity> getProducts() {
        return repository.findAll();
    }

    public Optional<ProductEntity> getProduct(int id) {
        return repository.findById(id);
    }
}
