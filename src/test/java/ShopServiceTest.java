import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest
{
    ProductRepo productRepo = new ProductRepo();
    OrderRepo orderRepo = new OrderListRepo();
    ShopService shopService = new ShopService(orderRepo, productRepo);

    @Test
    void addOrderTest() {
        //GIVEN

        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")),OrderState.PROCESSING);
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_expectNull() {
        //GIVEN
        List<String> productsIds = List.of("abc");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        assertNull(actual);
    }

    @Test
    public void getOrderByStateTest()
    {
        //GIVEN
        Product p1 = shopService.getProdcutById("1");
        Product p2 = shopService.getProdcutById("2");

        Order order1 = new Order("1", List.of(p1), OrderState.PROCESSING);
        Order order2 = new Order("2", List.of(p2), OrderState.IN_DELIVERY);

        shopService.getOrderRepo().getOrders().add(order1);
        shopService.getOrderRepo().getOrders().add(order2);

        //WHEN
        List<Order> actual = shopService.getOrderByOderState(OrderState.PROCESSING);

        assertEquals(true , actual.size() == 1);
        //assertTrue(actual.get(1).orderState().equals(OrderState.PROCESSING));
    }

    @Test
    public void updateOrderTest() {
        //GIVEN
        Product p1 = shopService.getProdcutById("1");
        Product p2 = shopService.getProdcutById("2");

        List<String> productIds = new ArrayList<>();
        productIds.add(p1.id());
        productIds.add(p2.id());

        Order addedOrder =  shopService.addOrder(productIds);

        //WHEN
        Optional<Order> actual =  shopService.updateOrder(addedOrder.id(), OrderState.IN_DELIVERY);
        Order expected = shopService.getOrderRepo().getOrderById(addedOrder.id());

        // THEN
        assertEquals(expected, actual.get());

    }

}
