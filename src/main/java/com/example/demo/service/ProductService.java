package com.example.demo.service;

import com.example.demo.dtos.ProductRequestDTO;
import com.example.demo.dtos.ProductResponseDTO;
import com.example.demo.entity.Product;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    // criar um produto novo
    public ProductResponseDTO createProduct(ProductRequestDTO dto) {

        if (productRepository.existsByNameIgnoreCase(dto.name())) {
            throw new IllegalArgumentException("Nome já cadastrado");
        }

        Product product = new Product();
        product.setName(dto.name());
        product.setPrice(dto.price());

        Product productSave = productRepository.save(product);

        return productMapper.toResponseDTO(productSave);
    }


    //listar todos os produtos
    public List<ProductResponseDTO> findAllProducts() {
        return productRepository.findAll()
                .stream()

                // convertendo entidade para DTO response
                .map(productMapper::toResponseDTO)
                .toList();
    }


    // encontrar por id
    public ProductResponseDTO findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado pelo ID: " + id));

        return productMapper.toResponseDTO(product);

        // conversão manual de entity para DTO
/*        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice()
        );*/
    }

    // atualizar produto
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado pelo ID: " + id));

            product.setName(dto.name());
            product.setPrice(dto.price());

            productRepository.save(product);

            return productMapper.toResponseDTO(product);

        }


    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado pelo ID: " + id));

        productRepository.delete(product);
    }
}
