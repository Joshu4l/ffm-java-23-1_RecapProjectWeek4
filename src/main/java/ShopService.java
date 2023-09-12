import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ShopService
{
    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;

    public ShopService(OrderRepo orderRepo, ProductRepo productRepo)
    {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    public OrderRepo getOrderRepo()
    {
        return orderRepo;
    }

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product productToOrder = productRepo.getProductById(productId);
            if (productToOrder == null) {
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                return null;
            }
            products.add(productToOrder);
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderState.PROCESSING);

        return orderRepo.addOrder(newOrder);
    }

    public Product getProdcutById(String id)
    {
        return productRepo.getProductById(id);
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
