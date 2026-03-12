package com.restaurant.restaurantmenu.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MenuItemDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @Min(value = 1, message = "Price must be greater than 0")
    private double priceUah;

    private double priceUsd;

    private String categoryName;
}