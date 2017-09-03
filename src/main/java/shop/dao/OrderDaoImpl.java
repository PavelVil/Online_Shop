package shop.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import shop.model.Order;
import shop.model.OrderDetails;
import shop.model.Product;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDaoImpl implements OrderDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addOrder(Map<Product, Integer> products, Order order) {
        Session session = sessionFactory.getCurrentSession();

        int totalPrice = 0;

        for(Iterator<Map.Entry<Product, Integer>> iterator = products.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<Product, Integer> entry = iterator.next();
            totalPrice += entry.getValue() * entry.getKey().getPrice();
        }

        order.setTotalPrice(totalPrice);
        session.save(order);

        for(Iterator<Map.Entry<Product, Integer>> iterator = products.entrySet().iterator(); iterator.hasNext();) {
            OrderDetails details = new OrderDetails();
            Map.Entry<Product, Integer> entry = iterator.next();
            details.setOrder(order);
            details.setProduct(entry.getKey());
            details.setQuantity(entry.getValue());
            details.setPrice(entry.getValue() * entry.getKey().getPrice());
            session.save(details);
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return sessionFactory.getCurrentSession().createQuery("from Order").list();
    }

    @Override
    public Order getById(Long id) {
        return sessionFactory.getCurrentSession().get(Order.class, id);
    }

    @Override
    public void deleteOrder(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Order order = session.get(Order.class, id);
        if (order != null) {
            session.delete(order);
        }
    }
}
