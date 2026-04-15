package com.example.pcmall.cart;

import com.example.pcmall.product.Product;
import com.example.pcmall.product.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartService {

    private final ProductService productService;

    public CartService(ProductService productService) {
        this.productService = productService;
    }

    public void addItem(ShoppingCart cart, Long productId, int quantity) {
        Product product = productService.getRequiredProduct(productId);
        int requestedQuantity = requirePositive(quantity);
        int nextQuantity = cart.quantityOf(productId) + requestedQuantity;
        cart.put(productId, clampToStock(product, nextQuantity));
    }

    public void updateItem(ShoppingCart cart, Long productId, int quantity) {
        Product product = productService.getRequiredProduct(productId);
        int requestedQuantity = requirePositive(quantity);
        cart.put(productId, clampToStock(product, requestedQuantity));
    }

    public void removeItem(ShoppingCart cart, Long productId) {
        cart.remove(productId);
    }

    public void clear(ShoppingCart cart) {
        cart.clear();
    }

    public CartSummary summarize(ShoppingCart cart) {
        List<CartLine> lines = new ArrayList<>();
        int totalQuantity = 0;
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Map.Entry<Long, Integer> entry : cart.quantities().entrySet()) {
            productService.findById(entry.getKey()).ifPresent(product -> {
                int quantity = Math.min(entry.getValue(), product.stock());
                BigDecimal subtotal = product.price().multiply(BigDecimal.valueOf(quantity));
                lines.add(new CartLine(product, quantity, subtotal));
            });
        }

        for (CartLine line : lines) {
            totalQuantity += line.quantity();
            totalPrice = totalPrice.add(line.subtotal());
        }

        return new CartSummary(List.copyOf(lines), totalQuantity, totalPrice);
    }

    private static int requirePositive(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }
        return quantity;
    }

    private static int clampToStock(Product product, int quantity) {
        if (!product.inStock()) {
            throw new IllegalArgumentException("Product is out of stock");
        }
        return Math.min(quantity, product.stock());
    }
}
