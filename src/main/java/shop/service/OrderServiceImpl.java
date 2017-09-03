package shop.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.OrderDao;
import shop.model.Order;
import shop.model.Product;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }


    @Override
    public void addOrder(Map<Product, Integer> products, Order order) {
        orderDao.addOrder(products, order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    @Transactional(readOnly = true)
    public Order getById(Long id) {
        return orderDao.getById(id);
    }

    @Override
    public void deleteOrder(Long id) {
        orderDao.deleteOrder(id);
    }
}
