package com.restaurant.restaurantmenu.repository;

import com.restaurant.restaurantmenu.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}