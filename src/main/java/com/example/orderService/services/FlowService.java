package com.example.orderService.services;

import com.example.orderService.model.DeliveryCompany;
import com.example.orderService.model.Order;
import com.example.orderService.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@SuppressWarnings("unused")
public class FlowService {

    public void saveOrder(Order order) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("target/generated-sources/orders/order_"+order.getId()+".json"), order);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<DeliveryCompany> getDeliveries() {
        List<DeliveryCompany> deliveries = new ArrayList<>();
        deliveries.add(new DeliveryCompany("Яндекс.Еда", "60 мин", 150));
        deliveries.add(new DeliveryCompany("Самокат", "70 мин", 120));
        deliveries.add(new DeliveryCompany("Деливери", "40 мин", 200));
        deliveries.add(new DeliveryCompany("Самая быстрая доставка", "300 мин", 50));
        deliveries.add(new DeliveryCompany("Курьер+", "30 мин", 250));
        return deliveries;
    }

    public List<Product> getCatalogProducts() {
        List<Product> catalogProducts = new ArrayList<>();
        catalogProducts.add(new Product("Гавайская", "куриное филе, ананас, болгарский перец, помидоры, сыр моцарелла, соус маджорио",
                700, Product.Size.MEDIUM.getName()));
        catalogProducts.add(new Product("Острая с беконом", "халапеньо, пепперони, бекон, соус маджорио, помидоры, соус томатный",
                900, Product.Size.LARGE.getName()));
        catalogProducts.add(new Product("Маргарита", "помидоры, орегано, сыр моцарелла, соус томатный, соус маджорио",
                500, Product.Size.SMALL.getName()));
        catalogProducts.add(new Product("Баварская", "сыр моцарелла, соус маджорио, лук красный, сервелат, ветчина, горчичный соус, лук хрустящий",
                540, Product.Size.SMALL.getName()));
        catalogProducts.add(new Product("Сливочная с курицей", "сыр моцарелла, соус маджорио, орегано, куриное филе, филадельфия",
                680, Product.Size.MEDIUM.getName()));
        catalogProducts.add(new Product("Пепперони", "пепперони, сыр моцарелла, соус томатный",
                550, Product.Size.MEDIUM.getName()));
        catalogProducts.add(new Product("Сырная", "сыр моцарелла, соус маджорио, орегано",
                580, Product.Size.MEDIUM.getName()));
        catalogProducts.add(new Product("Четыре сезона", "укроп, шампиньоны, куриное филе, орегано, ветчина, соус маджорио, пепперони, соус, сыр моцарелла, шампиньоны, помидоры",
                950, Product.Size.LARGE.getName()));
        catalogProducts.add(new Product("Бекон и лук", "сыр моцарелла, лук красный, ветчина, бекон",
                880, Product.Size.LARGE.getName()));
        catalogProducts.add(new Product("Капричоза", "ветчина, шампиньоны, маслины, маринованные огурцы, сыр моцарелла, соус томатный, соус маджорио, орегано",
                890, Product.Size.LARGE.getName()));
        return catalogProducts;
    }

    public DeliveryCompany findDeliveryByName(String name) {
        DeliveryCompany searchedDelivery = null;
        for (DeliveryCompany deliveryCompany : getDeliveries()) {
            if (deliveryCompany.getName().equals(name)) {
                searchedDelivery = deliveryCompany;
            }
        }
        return searchedDelivery;
    }

    public List<String> getMoreProductDetails(List<String> productNames) {
        List<String> result = new ArrayList<>();
        for (Product product : getCatalogProducts()) {
            if (productNames.contains(product.getName())) {
                result.add(product.getName()
                        .concat(" ")
                        .concat(product.getSize().toLowerCase())
                        .concat(" ")
                        .concat(String.valueOf(product.getPrice()))
                        .concat(" руб"));
            }
        }
        return result;
    }
}
