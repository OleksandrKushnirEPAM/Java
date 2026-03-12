package com.restaurant.restaurantmenu.service;

import com.restaurant.restaurantmenu.parser.PrivatBankClient;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateService {

    private final PrivatBankClient client;

    public ExchangeRateService(PrivatBankClient client) {
        this.client = client;
    }

    public double getUsdRate() {
        return client.getUsdRate();
    }
}