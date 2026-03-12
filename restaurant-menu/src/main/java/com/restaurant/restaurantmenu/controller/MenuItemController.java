package com.restaurant.restaurantmenu.controller;

import com.restaurant.restaurantmenu.dto.MenuItemDTO;
import com.restaurant.restaurantmenu.model.MenuItem;
import com.restaurant.restaurantmenu.service.MenuItemMapper;
import com.restaurant.restaurantmenu.service.MenuItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/menu")
public class MenuItemController {

    private final MenuItemService service;
    private final MenuItemMapper mapper;

    public MenuItemController(MenuItemService service, MenuItemMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<MenuItemDTO> getAll() {
        return service.getAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public MenuItemDTO create(@RequestBody @Valid MenuItemDTO dto) {
        if (dto.getPriceUah() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        MenuItem item = new MenuItem();
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPriceUah(dto.getPriceUah());
        MenuItem saved = service.save(item);
        return mapper.toDTO(saved);
    }
}