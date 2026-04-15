package com.example.pcmall.web;

import com.example.pcmall.cart.CartService;
import com.example.pcmall.cart.CartSummary;
import com.example.pcmall.cart.ShoppingCart;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {

    private final CartService cartService;
    private final ShoppingCart shoppingCart;

    public GlobalModelAttributes(CartService cartService, ShoppingCart shoppingCart) {
        this.cartService = cartService;
        this.shoppingCart = shoppingCart;
    }

    @ModelAttribute("cartSummary")
    public CartSummary cartSummary() {
        return cartService.summarize(shoppingCart);
    }
}
