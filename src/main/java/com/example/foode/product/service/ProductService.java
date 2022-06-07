package com.example.foode.product.service;

import com.example.foode.product.exception.ProductNotFoundException;
import com.example.foode.product.persistence.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Product createProduct(Product product) {
        var productEntity = productMapper.toProductEntity(product);
        return productMapper.fromProductEntity(productRepository.saveAndFlush(productEntity));
    }

    public Page<Product> getProductsByName(String name,
                                           Pageable pageable) {
        return productRepository.findAllByNameContainingIgnoreCase(name, pageable)
                .map(productMapper::fromProductEntity);
    }

    public Product getProduct(Long id) {
        return productMapper.fromProductEntity(productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id)));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Product newProduct,
                                 Long id) {
        return productRepository.findById(id)
                .map(product -> cloneAndSaveProduct(newProduct, productMapper.fromProductEntity(product)))
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    private Product cloneAndSaveProduct(Product fromProduct,
                                        Product toProduct) {
        toProduct.setExpirationDate(fromProduct.getExpirationDate());
        toProduct.setName(fromProduct.getName());

        var productEntity = productMapper.toProductEntity(toProduct);
        return productMapper.fromProductEntity(productRepository.saveAndFlush(productEntity));
    }
}
