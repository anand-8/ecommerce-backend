package com.anand.ecommerce_backend.dto;
import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequestDTO {
    @NotBlank(message = "product name is required")
    private String name;
    @NotBlank(message = "Description is required")
    private String description;
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;
    @NotNull(message = "Stock is required")
    @PositiveOrZero(message = "Stock cannot be negative")
    private Integer stock;
    @NotNull(message = "Category ID is required")
    private Long categoryId;



}
