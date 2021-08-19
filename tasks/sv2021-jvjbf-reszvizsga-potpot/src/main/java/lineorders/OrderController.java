package lineorders;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @DeleteMapping
    public void deleteAll() {
        orderService.deleteAll();
    }

    @GetMapping
    public List<OrderDTO> getOrders(@RequestParam(name = "month") Optional<Integer> month) {
        return orderService.getOrders(month);
    }

    @GetMapping("/{id}")
    public OrderDTO getOrder(@PathVariable(name = "id") Long id) {
        return orderService.getOrder(id);
    }

    @PostMapping
    public OrderDTO addOrder(@Valid @RequestBody CreateOrderCommand command) {
        return orderService.addOrder(command);
    }

    @PutMapping("/{id}/shipping")
    public OrderDTO upateOrder(@PathVariable(name = "id") Long id, @Valid @RequestBody ShippingCommand command) {
        return orderService.upateOrder(id, command);
    }

    @GetMapping("/shippingIncome")
    public int getIncome() {
        return orderService.getIncome();
    }

}
