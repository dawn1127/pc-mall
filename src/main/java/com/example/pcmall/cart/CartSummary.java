package com.example.pcmall.cart;

import java.math.BigDecimal;
import java.util.List;

public record CartSummary(List<CartLine> lines, int totalQuantity, BigDecimal totalPrice) {

    public boolean empty() {
        return lines.isEmpty();
    }
}
