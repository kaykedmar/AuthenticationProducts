package com.example.demo.mapper;

import com.example.demo.dtos.ProductResponseDTO;
import com.example.demo.entity.Product;
import org.springframework.stereotype.Component;

@Component // classe gerenciada pelo spring
public class ProductMapper {

    public ProductResponseDTO toResponseDTO(Product product){
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice()
                );
    }

    public Product toEntity(ProductResponseDTO dto){
        Product product = new Product();
        product.setPrice(dto.price());
        product.setName(dto.name());

        return product;
    }

}
