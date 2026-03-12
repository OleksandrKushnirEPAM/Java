package com.restaurant.restaurantmenu.service;

import com.restaurant.restaurantmenu.dto.MenuItemDTO;
import com.restaurant.restaurantmenu.model.MenuItem;
import org.springframework.stereotype.Component;

@Component
public class MenuItemMapper {

    private final ExchangeRateService rateService;

    public MenuItemMapper(ExchangeRateService rateService) {
        this.rateService = rateService;
    }

    public MenuItemDTO toDTO(MenuItem item) {
        MenuItemDTO dto = new MenuItemDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setPriceUah(item.getPriceUah());
        dto.setPriceUsd(Math.round(item.getPriceUah() / rateService.getUsdRate() * 100.0) / 100.0);
        if (item.getCategory() != null)
            dto.setCategoryName(item.getCategory().getName());
        return dto;
    }
}