import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ShopService
{
    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;


    public OrderRepo getOrderRepo()
    {
        return orderRepo;
    }

    /*
        NOTE: may return an empty order!
     */
    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {

            try {
                // potential query result
                Optional<Product> productToOrder = productRepo.getProductById(productId);
                // population of a list of products to be ordered using the query result above
                products.add(productToOrder.get());

            } catch (Exception e) {
                return null;
            }
        }
        // preparation of a new order based on the list above
        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderState.PROCESSING, Instant.now());
        return orderRepo.addOrder(newOrder);

            /*
            HANDLING VIA OPTIONAL:
            Optional<Product> productToOrder = productRepo.getProductById(productId);

            if (productToOrder.isPresent()) {
                products.add(productToOrder.get());
            } else {
                return null;
            }
            Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderState.PROCESSING);
            return orderRepo.addOrder(newOrder);
             */
    }

    public Optional<Order> updateOrder (String orderId, OrderState orderState) {
        Optional<Order> optionalOrder = Optional.empty();
        optionalOrder  = orderRepo.getOrders().stream()
                .filter(order -> order.id().equals(orderId))
                .findFirst();
        Order updatedOrder = optionalOrder.get().withOrderState(orderState);
        orderRepo.removeOrder(orderId);
        orderRepo.addOrder(updatedOrder);
        return Optional.ofNullable(updatedOrder);
    }


    public List<Product> getProducts() {
        return productRepo.getProducts();
    }

    public Product getProdcutById(String id)
    {
        if (productRepo.getProductById(id).isPresent()) {
            return productRepo.getProductById(id).get();
    }
        return null;
    }



    //Schreibt in dem ShopService eine Methode, die alle Bestellungen mit einem bestimmten Bestellstatus (Parameter)
    // in einer Liste zurückgibt. Nutzt dafür Streams
    public List<Order> getOrderByOderState(OrderState state)
    {
        List<Order>  result  = orderRepo.getOrders().stream().filter(
                order -> order.orderState() == state).
                collect(Collectors.toList());
        return result;
    }
}
