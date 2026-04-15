package com.example.pcmall.web;

import com.example.pcmall.cart.CartService;
import com.example.pcmall.cart.CartSummary;
import com.example.pcmall.cart.ShoppingCart;
import com.example.pcmall.product.Product;
import com.example.pcmall.product.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mock")
public class MockApiController {

    private final ProductService productService;
    private final CartService cartService;
    private final ShoppingCart shoppingCart;

    public MockApiController(ProductService productService, CartService cartService, ShoppingCart shoppingCart) {
        this.productService = productService;
        this.cartService = cartService;
        this.shoppingCart = shoppingCart;
    }

    @GetMapping("/products")
    public List<Product> products() {
        return productService.findAll();
    }

    @GetMapping("/cart")
    public CartSummary cart() {
        return cartService.summarize(shoppingCart);
    }
}
