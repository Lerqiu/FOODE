package com.example.foode.product.presentation;

import com.example.foode.product.service.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDTOMapper {

    ProductDTO toProductDTO(Product product);

    Product fromProductDTO(ProductDTO productDTO);

}
