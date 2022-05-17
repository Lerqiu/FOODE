package com.example.foode.Product;

import com.example.foode.Product.Exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    public Page<Product> getProductsByName(String name,
                                           Pageable pageable) {
        return productRepository.findAllByNameContainingIgnoreCase(name, pageable);
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Product newProduct,
                                 Long id) {
        return productRepository.findById(id)
                .map(product -> cloneAndSaveProduct(product, newProduct))
                .orElseGet(() -> saveNewProduct(newProduct, id));
    }

    private Product cloneAndSaveProduct(Product fromProduct,
                                        Product toProduct) {
        toProduct.setExpirationDate(fromProduct.getExpirationDate());
        toProduct.setName(fromProduct.getName());
        return productRepository.saveAndFlush(toProduct);
    }

    private Product saveNewProduct(Product product,
                                   Long id) {
        product.setId(id);
        return productRepository.saveAndFlush(product);
    }
}
