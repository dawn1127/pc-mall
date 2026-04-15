package com.example.pcmall.cart;

import com.example.pcmall.product.Product;

import java.math.BigDecimal;

public record CartLine(Product product, int quantity, BigDecimal subtotal) {
}
