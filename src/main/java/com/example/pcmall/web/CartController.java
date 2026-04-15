package com.example.pcmall.web;

import com.example.pcmall.cart.CartService;
import com.example.pcmall.cart.ShoppingCart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CartController {

    private final CartService cartService;
    private final ShoppingCart shoppingCart;

    public CartController(CartService cartService, ShoppingCart shoppingCart) {
        this.cartService = cartService;
        this.shoppingCart = shoppingCart;
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("cart", cartService.summarize(shoppingCart));
        return "cart";
    }

    @PostMapping("/cart/items")
    public String addItem(@RequestParam Long productId,
                          @RequestParam(defaultValue = "1") int quantity,
                          RedirectAttributes redirectAttributes) {
        try {
            cartService.addItem(shoppingCart, productId, quantity);
            redirectAttributes.addFlashAttribute("notice", "Added to cart.");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/products";
    }

    @PostMapping("/cart/items/update")
    public String updateItem(@RequestParam Long productId,
                             @RequestParam int quantity,
                             RedirectAttributes redirectAttributes) {
        try {
            cartService.updateItem(shoppingCart, productId, quantity);
            redirectAttributes.addFlashAttribute("notice", "Cart updated.");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/items/remove")
    public String removeItem(@RequestParam Long productId, RedirectAttributes redirectAttributes) {
        cartService.removeItem(shoppingCart, productId);
        redirectAttributes.addFlashAttribute("notice", "Item removed.");
        return "redirect:/cart";
    }

    @PostMapping("/cart/clear")
    public String clear(RedirectAttributes redirectAttributes) {
        cartService.clear(shoppingCart);
        redirectAttributes.addFlashAttribute("notice", "Cart cleared.");
        return "redirect:/cart";
    }
}
