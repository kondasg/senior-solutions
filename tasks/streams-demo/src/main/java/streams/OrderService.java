package streams;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService {

    private List<Order> orders = new ArrayList<>();

    public void saveOrder(Order order) {
        orders.add(order);
    }

    // Határozd meg a rendelések darabszámát egy paraméterként kapott státusz alapján
    public long countOrdersByStatus(String status) {
        return orders.stream()
                .filter(o -> o.getStatus().equals(status))
                .count();
    }

    // Gyűjtsd össze azokat a rendeléseket, amelyekben van egy paraméterként kapott kategóriájú termék
    public List<Order> collectOrdersWithProductCategory(String category) {
        return orders.stream()
                .filter(o -> o.getProducts().stream()
                        .anyMatch(p -> p.getCategory().equals(category)))
                .collect(Collectors.toList());
    }

    // Gyűjtsd össze azokat a termékeket a rendelések közül, amelyeknek egy paraméteról kapott összegnél magasabb az áruk
    public List<Product> productsOverAmountPrice(int amount) {
        return orders.stream()
                .flatMap(o -> o.getProducts().stream())
                .filter(p -> p.getPrice() > amount)
                // .distinct() // egy termék csak 1x
                .collect(Collectors.toList());
    }


    // Írj egy metódust ami paraméterként vár két dátumot, és adjuk vissza a két dátum közötti árbevételt,
    // vagyis a két dátum közötti rendelések termékeinek az összértékét!
    public double getIncomeBetweenTwoDates(LocalDate date1, LocalDate date2) {
        return orders.stream()
                .filter(o -> o.getOrderDate().isAfter(date1) && o.getOrderDate().isBefore(date2))
                .flatMap(o -> o.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .sum();
    }

    // Keressünk meg egy terméket a neve alapján, amit paraméterként lehet megadni.
    public Product searchProductByName(String productName) {
        return orders.stream()
                .flatMap(o -> o.getProducts().stream()
                .filter(p -> p.getName().equals(productName)))
                .findFirst()
                .orElse(null);
    }

    // Adjuk vissza azt a rendelést, ami a legdrágább terméket tartalmazza. Ha több ilyen van bármelyiket!
    public Order getOrderWithExpensiveProduct() {
        double mostExpensive = 0;
        Order mostExpensiveOrder = orders.get(0);
        for (Order order : orders) {
            for (Product product : order.getProducts()) {
                if (mostExpensive < product.getPrice()) {
                    mostExpensive = product.getPrice();
                    mostExpensiveOrder = order;
                }
            }
        }
        return mostExpensiveOrder;
    }
}
