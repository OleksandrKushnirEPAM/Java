package com.restaurant.restaurantmenu.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PrivatBankClient {

    private static final String API_URL =
            "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11";

    public double getUsdRate() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(API_URL, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            for (JsonNode node : root) {
                if (node.get("ccy").asText().equals("USD")) {
                    return node.get("sale").asDouble();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 40.0;
    }
}