package shop.dao;


import shop.model.Order;
import shop.model.Product;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    void addOrder(Map<Product, Integer> products, Order order);

    List<Order> getAllOrders();

    Order getById(Long id);

    void deleteOrder(Long id);

}
