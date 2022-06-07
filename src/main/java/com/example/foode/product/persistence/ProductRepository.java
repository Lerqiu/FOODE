package com.example.foode.product.persistence;

import com.example.foode.product.persistence.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Page<ProductEntity> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
