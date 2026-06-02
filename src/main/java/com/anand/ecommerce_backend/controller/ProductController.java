package com.anand.ecommerce_backend.controller;

import jakarta.validation.Valid;
import com.anand.ecommerce_backend.dto.ProductRequestDTO;
import com.anand.ecommerce_backend.dto.ProductResponseDTO;
import com.anand.ecommerce_backend.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;

    }
    @PostMapping
    public ProductResponseDTO createProduct(@Valid @RequestBody ProductRequestDTO dto){

        return productService.createProduct(dto);
    }

    @GetMapping
    public Page<ProductResponseDTO> getAllProduct(
             @RequestParam(defaultValue = "0") int page,
             @RequestParam(defaultValue = "10") int size
    ){
        return productService.getAllProducts(page,size);
    }

}
