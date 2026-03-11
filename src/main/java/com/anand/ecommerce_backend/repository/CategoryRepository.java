package com.anand.ecommerce_backend.repository;

import com.anand.ecommerce_backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}