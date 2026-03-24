package com.restaurant.restaurantmenu.service;

import com.restaurant.restaurantmenu.model.Category;
import com.restaurant.restaurantmenu.model.MenuItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class MenuParserService {

    private final MenuItemService menuService;
    private final CategoryService categoryService;

    public MenuParserService(MenuItemService menuService, CategoryService categoryService) {
        this.menuService = menuService;
        this.categoryService = categoryService;
    }

    public void parseAndSave() {
        try {
            String url = "https://smakplus.com/recipecat/desserts/page/5/";
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(10_000)
                    .get();

            Elements items = doc.select("h2");

            Category category = categoryService.getAll()
                    .stream()
                    .findFirst()
                    .orElseGet(() -> {
                        Category newCat = new Category();
                        newCat.setName("Parsed");
                        return categoryService.save(newCat);
                    });

            items.forEach(element -> {
                String name = element.text().trim();
                if (name.isEmpty()) return;

                MenuItem item = new MenuItem();
                item.setName(name);
                item.setDescription("Parsed from SMAKPLUS");
                item.setPriceUah(100);
                item.setCategory(category);
                menuService.save(item);
            });

        } catch (Exception e) {
            System.out.println("Error parsing SMAKPLUS: " + e.getMessage());
        }
    }
}