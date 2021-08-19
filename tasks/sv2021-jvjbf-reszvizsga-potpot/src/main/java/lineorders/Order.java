package lineorders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_mumber")
    private String productNumber;
    @Column(name = "order_date")
    private LocalDate orderDate;
    @Column(name = "shipping_price")
    private int shippingPrice;
    @Column(name = "shipping_date")
    private LocalDate shippingDate;

    public Order(String productNumber) {
        this.productNumber = productNumber;
        this.orderDate = LocalDate.now();
    }
}
