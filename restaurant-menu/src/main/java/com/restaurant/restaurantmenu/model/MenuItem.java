package com.restaurant.restaurantmenu.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private double priceUah;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}