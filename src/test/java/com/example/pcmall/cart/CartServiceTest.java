package com.example.pcmall.cart;

import com.example.pcmall.product.MockProductRepository;
import com.example.pcmall.product.ProductService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CartServiceTest {

    private final ProductService productService = new ProductService(new MockProductRepository());
    private final CartService cartService = new CartService(productService);
    private final ShoppingCart cart = new ShoppingCart();

    @Test
    void addItemAccumulatesQuantityForSameProduct() {
        cartService.addItem(cart, 1L, 1);
        cartService.addItem(cart, 1L, 2);

        CartSummary summary = cartService.summarize(cart);

        assertThat(summary.totalQuantity()).isEqualTo(3);
        assertThat(summary.lines()).hasSize(1);
        assertThat(summary.totalPrice()).isEqualByComparingTo(new BigDecimal("38997.00"));
    }

    @Test
    void addItemClampsQuantityToStock() {
        cartService.addItem(cart, 1L, 99);

        CartSummary summary = cartService.summarize(cart);

        assertThat(summary.totalQuantity()).isEqualTo(9);
    }

    @Test
    void soldOutProductCannotBeAdded() {
        assertThatThrownBy(() -> cartService.addItem(cart, 8L, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product is out of stock");
    }

    @Test
    void updateItemRejectsNonPositiveQuantity() {
        cartService.addItem(cart, 1L, 1);

        assertThatThrownBy(() -> cartService.updateItem(cart, 1L, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Quantity must be at least 1");
    }

    @Test
    void removeAndClearItems() {
        cartService.addItem(cart, 1L, 1);
        cartService.addItem(cart, 2L, 1);

        cartService.removeItem(cart, 1L);
        assertThat(cartService.summarize(cart).totalQuantity()).isEqualTo(1);

        cartService.clear(cart);
        assertThat(cartService.summarize(cart).empty()).isTrue();
    }
}
