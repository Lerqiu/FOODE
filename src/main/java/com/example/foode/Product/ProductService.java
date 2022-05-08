package com.example.foode.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Product createProduct(Product product);

    Page<Product> getProductsByName(String name,
                                    Pageable pageable);

    Product getProduct(Long id);

    void deleteProduct(Long id);

    Product updateProduct(Product newProduct,
                          Long id);

}
