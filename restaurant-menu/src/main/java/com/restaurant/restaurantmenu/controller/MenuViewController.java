package com.restaurant.restaurantmenu.controller;

import com.restaurant.restaurantmenu.model.Category;
import com.restaurant.restaurantmenu.model.MenuItem;
import com.restaurant.restaurantmenu.service.CategoryService;
import com.restaurant.restaurantmenu.service.MenuItemService;
import com.restaurant.restaurantmenu.service.MenuItemMapper;
import com.restaurant.restaurantmenu.dto.MenuItemDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MenuViewController {

    private final MenuItemService menuService;
    private final CategoryService categoryService;
    private final MenuItemMapper mapper;

    public MenuViewController(MenuItemService menuService, CategoryService categoryService, MenuItemMapper mapper) {
        this.menuService = menuService;
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

    @GetMapping("/menu/view")
    public String viewMenu(Model model) {
        List<MenuItemDTO> items = menuService.getAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());

        List<Category> categories = categoryService.getAll();

        model.addAttribute("menuItems", items);
        model.addAttribute("categories", categories);

        return "menu";
    }
    @GetMapping("/menu/edit/{id}")
    public String editDishForm(@PathVariable Long id, Model model) {
        MenuItem item = menuService.getById(id).orElse(null);
        if (item == null) {
            return "redirect:/menu/view";
        }
        List<Category> categories = categoryService.getAll();
        model.addAttribute("menuItem", item);
        model.addAttribute("categories", categories);
        return "menu-edit"; // новий HTML шаблон
    }

    @PostMapping("/menu/edit/{id}")
    public String updateDish(@PathVariable Long id,
                             @RequestParam String name,
                             @RequestParam String description,
                             @RequestParam double priceUah,
                             @RequestParam Long categoryId) {

        if (priceUah <= 0) priceUah = 1;

        MenuItem item = menuService.getById(id).orElse(null);
        if (item != null) {
            item.setName(name);
            item.setDescription(description);
            item.setPriceUah(priceUah);
            Category category = categoryService.getById(categoryId).orElse(null);
            item.setCategory(category);
            menuService.save(item);
        }
        return "redirect:/menu/view";
    }

    @PostMapping("/menu/add")
    public String addDish(@RequestParam String name,
                          @RequestParam String description,
                          @RequestParam double priceUah,
                          @RequestParam Long categoryId) {

        Category category = categoryService.getById(categoryId).orElse(null);
        if (category != null) {
            MenuItem item = new MenuItem();
            item.setName(name);
            item.setDescription(description);
            item.setPriceUah(priceUah);
            item.setCategory(category);
            menuService.save(item);
        }

        return "redirect:/menu/view";
    }
    @PostMapping("/menu/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        menuService.delete(id);
        return "redirect:/menu/view";
    }
}