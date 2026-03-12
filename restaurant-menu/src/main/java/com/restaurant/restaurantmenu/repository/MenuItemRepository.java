package com.restaurant.restaurantmenu.repository;

import com.restaurant.restaurantmenu.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}