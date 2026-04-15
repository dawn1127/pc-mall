# PC Mall

A two-page Spring Boot MVC demo mall that uses mock backend data first.

## Pages

- `GET /products` - product list
- `GET /cart` - shopping cart
- `GET /api/mock/products` - mock product data for debugging
- `GET /api/mock/cart` - current session cart data for debugging

## Run

```powershell
mvn spring-boot:run
```

If `mvn` is not in PATH, run the same Maven goal from your IDE or add Maven's `bin` directory to PATH first.

The app runs at:

```text
http://localhost:8080/products
```

## Data Mode

This version does not connect to MySQL. Products are returned by `MockProductRepository`, while the cart is stored in the user's HTTP session.
