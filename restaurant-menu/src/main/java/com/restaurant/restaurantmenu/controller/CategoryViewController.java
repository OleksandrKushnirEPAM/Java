package com.restaurant.restaurantmenu.controller;

import com.restaurant.restaurantmenu.model.Category;
import com.restaurant.restaurantmenu.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryViewController {

    private final CategoryService categoryService;

    public CategoryViewController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String showCategories(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "categories";
    }

    @PostMapping("/categories/add")
    public String addCategory(@RequestParam String name) {

        if(name == null || name.trim().isEmpty()){
            return "redirect:/categories";
        }

        Category category = new Category();
        category.setName(name);

        categoryService.save(category);

        return "redirect:/categories";
    }

    @PostMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}