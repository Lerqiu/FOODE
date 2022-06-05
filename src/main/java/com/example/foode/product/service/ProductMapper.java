package com.example.foode.product.service;

import com.example.foode.product.persistence.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductEntity toProductEntity(Product product);

    Product fromProductEntity(ProductEntity productEntity);

    List<Product> fromProductEntities(List<ProductEntity> productEntities);

}
