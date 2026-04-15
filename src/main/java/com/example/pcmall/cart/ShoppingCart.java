package com.example.pcmall.cart;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@SessionScope
public class ShoppingCart implements Serializable {

    private final Map<Long, Integer> quantities = new LinkedHashMap<>();

    public Map<Long, Integer> quantities() {
        return Collections.unmodifiableMap(quantities);
    }

    void put(Long productId, int quantity) {
        quantities.put(productId, quantity);
    }

    void remove(Long productId) {
        quantities.remove(productId);
    }

    void clear() {
        quantities.clear();
    }

    int quantityOf(Long productId) {
        return quantities.getOrDefault(productId, 0);
    }
}
