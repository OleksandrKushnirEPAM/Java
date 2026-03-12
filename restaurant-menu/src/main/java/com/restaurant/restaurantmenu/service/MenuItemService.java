package com.restaurant.restaurantmenu.service;

import com.restaurant.restaurantmenu.model.MenuItem;
import com.restaurant.restaurantmenu.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {

    private final MenuItemRepository repository;

    public MenuItemService(MenuItemRepository repository) {
        this.repository = repository;
    }

    public List<MenuItem> getAll() {
        return repository.findAll();
    }

    public Optional<MenuItem> getById(Long id) {
        return repository.findById(id);
    }

    public MenuItem save(MenuItem item) {
        return repository.save(item);
    }

    public MenuItem update(Long id, MenuItem item) {
        item.setId(id);
        return repository.save(item);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}