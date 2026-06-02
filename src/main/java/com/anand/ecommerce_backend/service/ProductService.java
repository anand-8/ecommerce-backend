package com.anand.ecommerce_backend.service;

import com.anand.ecommerce_backend.dto.ProductRequestDTO;
import com.anand.ecommerce_backend.dto.ProductResponseDTO;
import com.anand.ecommerce_backend.entity.Category;
import com.anand.ecommerce_backend.entity.Product;
import com.anand.ecommerce_backend.entity.Product;
import com.anand.ecommerce_backend.repository.CategoryRepository;
import com.anand.ecommerce_backend.repository.ProductRepository;

import com.anand.ecommerce_backend.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;

    }
    public ProductResponseDTO createProduct(ProductRequestDTO dto){
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Product product = Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .category(category)
                .isActive(true)
                .build();
        Product savedProduct = productRepository.save(product);
         return mapToResponse(savedProduct);

    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Page<ProductResponseDTO> getAllProducts(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return productRepository.findByIsActiveTrue(pageable)
        .map(this::mapToResponse);
    }
    private ProductResponseDTO mapToResponse(Product product){
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .categoryName(product.getCategory().getName())
                .build();
    }
}