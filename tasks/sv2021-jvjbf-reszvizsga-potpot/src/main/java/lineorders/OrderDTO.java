package lineorders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long id;
    private String productNumber;
    private LocalDate orderDate;
    private int shippingPrice;
    private LocalDate shippingDate;
}
