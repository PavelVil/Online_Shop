package shop.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import shop.model.*;

import java.util.List;

@Repository
public class ShopDaoImpl implements ShopDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Gender> getGenders() {
        return sessionFactory.getCurrentSession().createQuery("from Gender").list();
    }

    @Override
    public Gender getGenderById(Long id) {
        return sessionFactory.getCurrentSession().get(Gender.class, id);
    }

    @Override
    public List<Category> getAllCategory() {
        return sessionFactory.getCurrentSession().createQuery("from Category").list();
    }

    @Override
    public Category getCategoryById(Long id) {
        return sessionFactory.getCurrentSession().get(Category.class, id);
    }

    @Override
    public Category getCategoryByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Category where categoryName = :name").setString("name", name);
        return (Category)query.uniqueResult();
    }

    @Override
    public Gender getGenderByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Gender where genderName = :name").setString("name", name);
        return (Gender)query.uniqueResult();
    }
}
