package com.example.pcmall.product;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class MockProductRepository implements ProductRepository {

    private final List<Product> products = List.of(
            product(1L, "NB-TP-X1C-G12", "ThinkPad X1 Carbon Gen 12", "Lenovo", "Laptop",
                    "14-inch business laptop with Intel Core Ultra, 32GB memory, and a carbon-fiber chassis.",
                    "12999.00", 9,
                    "https://images.unsplash.com/photo-1517336714731-489689fd1ca8?auto=format&fit=crop&w=900&q=80"),
            product(2L, "NB-MBP-14-M3", "MacBook Pro 14", "Apple", "Laptop",
                    "Compact creative workstation with a Liquid Retina XDR display and long battery life.",
                    "14999.00", 6,
                    "https://images.unsplash.com/photo-1541807084-5c52b6b3adef?auto=format&fit=crop&w=900&q=80"),
            product(3L, "NB-ROG-G16", "ROG Zephyrus G16", "ASUS", "Gaming Laptop",
                    "Slim gaming machine with high-refresh display, RTX graphics, and quiet performance modes.",
                    "15999.00", 4,
                    "https://images.unsplash.com/photo-1593642702821-c8da6771f0c6?auto=format&fit=crop&w=900&q=80"),
            product(4L, "MON-U2723QE", "UltraSharp 27 4K USB-C Monitor", "Dell", "Monitor",
                    "Color-accurate 4K workspace display with USB-C docking and thin bezels.",
                    "3999.00", 12,
                    "https://images.unsplash.com/photo-1527443224154-c4a3942d3acf?auto=format&fit=crop&w=900&q=80"),
            product(5L, "KEY-K8-PRO", "K8 Pro Mechanical Keyboard", "Keychron", "Keyboard",
                    "Wireless hot-swappable keyboard with aluminum frame and comfortable tactile switches.",
                    "699.00", 18,
                    "https://images.unsplash.com/photo-1587829741301-dc798b83add3?auto=format&fit=crop&w=900&q=80"),
            product(6L, "MSE-MX-MASTER-3S", "MX Master 3S Wireless Mouse", "Logitech", "Mouse",
                    "Quiet precision mouse with fast scrolling, ergonomic shape, and multi-device switching.",
                    "599.00", 22,
                    "https://images.unsplash.com/photo-1527864550417-7fd91fc51a46?auto=format&fit=crop&w=900&q=80"),
            product(7L, "SSD-990PRO-2TB", "990 PRO 2TB NVMe SSD", "Samsung", "Storage",
                    "High-speed PCIe 4.0 storage for creators, gaming builds, and compact workstations.",
                    "1299.00", 15,
                    "https://images.unsplash.com/photo-1591488320449-011701bb6704?auto=format&fit=crop&w=900&q=80"),
            product(8L, "GPU-RTX4070S", "GeForce RTX 4070 Super", "NVIDIA", "Graphics Card",
                    "Efficient graphics card for high-refresh 1440p gaming, 3D work, and AI-assisted apps.",
                    "4899.00", 0,
                    "https://images.unsplash.com/photo-1591405351990-4726e331f141?auto=format&fit=crop&w=900&q=80")
    ).stream()
            .sorted(Comparator.comparing(Product::id))
            .toList();

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return products.stream()
                .filter(product -> product.id().equals(id))
                .findFirst();
    }

    private static Product product(Long id, String sku, String name, String brand, String category,
                                   String description, String price, int stock, String imageUrl) {
        return new Product(id, sku, name, brand, category, description, new BigDecimal(price), stock, imageUrl);
    }
}
