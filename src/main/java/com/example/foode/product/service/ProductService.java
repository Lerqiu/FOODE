package com.example.foode.product.service;

import com.example.foode.product.persistence.ProductRepository;
import com.example.foode.product.exception.ProductNotFoundException;
import com.example.foode.product.persistence.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductEntity createProduct(ProductEntity productEntity) {
        return productRepository.saveAndFlush(productEntity);
    }

    public Page<ProductEntity> getProductsByName(String name,
                                                 Pageable pageable) {
        return productRepository.findAllByNameContainingIgnoreCase(name, pageable);
    }

    public ProductEntity getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public ProductEntity updateProduct(ProductEntity newProductEntity,
                                       Long id) {
        return productRepository.findById(id)
                .map(product -> cloneAndSaveProduct(product, newProductEntity))
                .orElseGet(() -> saveNewProduct(newProductEntity, id));
    }

    private ProductEntity cloneAndSaveProduct(ProductEntity fromProductEntity,
                                              ProductEntity toProductEntity) {
        toProductEntity.setExpirationDate(fromProductEntity.getExpirationDate());
        toProductEntity.setName(fromProductEntity.getName());
        return productRepository.saveAndFlush(toProductEntity);
    }

    private ProductEntity saveNewProduct(ProductEntity productEntity,
                                         Long id) {
        productEntity.setId(id);
        return productRepository.saveAndFlush(productEntity);
    }
}
