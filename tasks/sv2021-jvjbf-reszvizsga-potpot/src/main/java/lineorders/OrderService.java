package lineorders;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderReository orderReository;
    private ModelMapper modelMapper;

    public void deleteAll() {
        orderReository.deleteAll();
    }

    public List<OrderDTO> getOrders(Optional<Integer> month) {
        Type targetListType = new TypeToken<List<OrderDTO>>() {}.getType();
        List<Order> filtered = month.isEmpty()
                ? orderReository.findAll()
                : orderReository.findByMonth(month.get());
        return modelMapper.map(filtered, targetListType);
    }

    public OrderDTO getOrder(Long id) {
        return modelMapper.map(getOrderById(id), OrderDTO.class);
    }

    public OrderDTO addOrder(CreateOrderCommand command) {
        Order order = new Order(command.getProductNumber());
        orderReository.save(order);
        return modelMapper.map(order, OrderDTO.class);
    }

    @Transactional
    public OrderDTO upateOrder(Long id, ShippingCommand command) {
        Order order = getOrderById(id);
        if (order.getShippingDate() == null) {
            order.setShippingPrice(command.getShippingPrice());
            order.setShippingDate(LocalDate.now());
        }
        return modelMapper.map(order, OrderDTO.class);
    }

    public int getIncome() {
        return orderReository.sumShippingIncome();
    }

    private Order getOrderById(Long id) {
        return orderReository.findById(id)
                .orElseThrow(() -> new NotOrderExpcetion("Not found orders ID"));
    }
}
