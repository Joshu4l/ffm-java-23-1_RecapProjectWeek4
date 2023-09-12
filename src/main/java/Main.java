import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//Uuuupps
        /*
        Erstellt eine Main-Klasse mit main-Methode. In dieser Methode erstellt eine Instanz des Shop-Services.
        Auch die konkreten Instanzen für OrderRepo und ShopRepo sollen hier in der main-Methode erstellt werden.
        Übergebt sie dem ShopService-Konstruktor. Nutzt die @RequiredArgsConstructor-Annotation im Shop-Service,
        um einen entsprechenden Konstruktor zu generieren.
        Legt drei konkrete Bestellungen fest und fügt sie alle dem ShopService hinzu.
         */

        OrderListRepo myOrderListRepo = new OrderListRepo();
        ProductRepo myProductRepo = new ProductRepo();

        ShopService myShopService = new ShopService(myOrderListRepo, myProductRepo);
        List<Product> availableProducts = myShopService.getProducts();

        List<String> productIdsToBeOrdered1 = availableProducts.stream()
                .map(product -> product.id())
                .limit(5)
                .collect(Collectors.toList());
        myShopService.addOrder(productIdsToBeOrdered1);

        Collections.shuffle(availableProducts);

        List<String> productIdsToBeOrdered2 = availableProducts.stream()
                .map(product -> product.id())
                .limit(5)
                .collect(Collectors.toList());
        myShopService.addOrder(productIdsToBeOrdered2);

        Collections.shuffle(availableProducts);

        List<String> productIdsToBeOrdered3 = availableProducts.stream()
                .map(product -> product.id())
                .limit(5)
                .collect(Collectors.toList());
        myShopService.addOrder(productIdsToBeOrdered3);


        System.out.println(myShopService.getOrderRepo());

        System.out.println(productIdsToBeOrdered1);
        System.out.println(productIdsToBeOrdered2);
        System.out.println(productIdsToBeOrdered3);


    }
}
