package utils;

import com.github.javafaker.Faker;
import models.*;

import java.util.ArrayList;
import java.util.List;

public class TestDataGenerator {

    static Faker faker = new Faker();

    public static Product createProduct() {

        Product product = new Product();

        product.title = faker.commerce().productName();
        product.price = Double.parseDouble(faker.commerce().price());
        product.description = faker.lorem().sentence();
        product.category = "Testing";
        product.image = "http://example.com";

        return product;
    }

    public static User createUser() {

        User user = new User();

        user.username = faker.name().username();
        user.email = faker.internet().emailAddress();
        user.password = "Test123_";

        return user;
    }

    public static Cart createCart() {

        Cart cart = new Cart();

        cart.userId = 1;
        cart.date = "2026-04-15";

        CartProduct item = new CartProduct();
        item.productId = 1;
        item.quantity = 2;

        List<CartProduct> products = new ArrayList<>();
        products.add(item);

        cart.products = products;

        return cart;
    }
}
