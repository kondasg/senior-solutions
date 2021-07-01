package streams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    OrderService ordersService = new OrderService();

    @BeforeEach
    public void init() {
        Product p1 = new Product("Tv", "IT", 2000);
        Product p2 = new Product("Laptop", "IT", 2400);
        Product p3 = new Product("Phone", "IT", 400);
        Product p4 = new Product("Lord of The Rings", "Book", 20);
        Product p5 = new Product("Harry Potter Collection", "Book", 120);

        Order o1 = new Order("pending", LocalDate.of(2021, 6, 7));
        o1.addProduct(p1);
        o1.addProduct(p2);
        o1.addProduct(p5);

        Order o2 = new Order("on delivery", LocalDate.of(2021, 6, 2));
        o2.addProduct(p3);
        o2.addProduct(p1);
        o2.addProduct(p2);

        Order o3 = new Order("pending", LocalDate.of(2021, 6, 3));
        o3.addProduct(p4);
        o3.addProduct(p3);
        o3.addProduct(p5);

        Order o4 = new Order("on delivery", LocalDate.of(2021, 6, 6));
        o4.addProduct(p3);
        o4.addProduct(p2);

        ordersService.saveOrder(o1);
        ordersService.saveOrder(o2);
        ordersService.saveOrder(o3);
        ordersService.saveOrder(o4);
    }

    @Test
    void countOrdersByStatusTest() {
        long res = ordersService.countOrdersByStatus("pending");
        assertEquals(2, res);
    }

    @Test
    void countOrdersByStatusZeroTest() {
        long res = ordersService.countOrdersByStatus("delete");
        assertEquals(0, res);
    }

    @Test
    void collectOrdersWithProductCategoryTest() {
        List<Order> res = ordersService.collectOrdersWithProductCategory("Book");
        assertEquals(2, res.size());
    }

    @Test
    void productsOverAmountPriceTest() {
        List<Product> res = ordersService.productsOverAmountPrice(1999);
        assertEquals(5, res.size());
    }


    @Test
    void getIncomeBetweenTwoDates() {
        assertEquals(5340, ordersService.getIncomeBetweenTwoDates(
                LocalDate.of(2021, 6, 1), LocalDate.of(2021, 6, 5)));
    }

    @Test
    void searchProductByNameTest() {
        Product product = ordersService.searchProductByName("Tv");
        assertEquals(2000, product.getPrice());
        assertNull(ordersService.searchProductByName("Lord"));
    }

    @Test
    void getOrderWithExpensiveProduct() {

    }

}