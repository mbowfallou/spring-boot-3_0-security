package com.groupeisi.repository;

import com.groupeisi.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<ProductEntity, Integer> {
}
