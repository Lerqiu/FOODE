package com.example.foode.product.presentation;

import com.example.foode.product.service.ProductService;
import com.example.foode.product.persistence.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductEntity createProduct(@RequestBody ProductEntity productEntity) {
        return productService.createProduct(productEntity);
    }

    @GetMapping
    public Page<ProductEntity> getProductsByName(@RequestParam String name,
                                                 Pageable pageable) {
        return productService.getProductsByName(name, pageable);
    }

    @GetMapping("/{id}")
    public ProductEntity getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductEntity updateProduct(@RequestBody ProductEntity productEntity,
                                       @PathVariable Long id) {
        return productService.updateProduct(productEntity, id);
    }

}
