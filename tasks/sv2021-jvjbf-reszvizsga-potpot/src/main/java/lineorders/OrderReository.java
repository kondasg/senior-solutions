package lineorders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderReository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders o WHERE MONTH(o.order_date) = :month", nativeQuery = true)
    List<Order> findByMonth(Integer month);

    @Query("SELECT SUM(o.shippingPrice) FROM Order o")
    int sumShippingIncome();
}
