package shop.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import shop.model.Category;
import shop.model.Gender;
import shop.model.Product;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Product").list();
    }

    @Override
    public List<Product> getAllByGender(Gender.GenderEnum gender) {
        if (gender == Gender.GenderEnum.MEN) {
            return sessionFactory.getCurrentSession().getNamedQuery("Product.getAllByGender").setParameter("gender",new Gender(1L,"men")).list();
        } else {
            return sessionFactory.getCurrentSession().getNamedQuery("Product.getAllByGender").setParameter("gender",new Gender(2L,"women")).list();
        }
    }

    @Override
    public Product getById(Long id) {
        Product product = sessionFactory.getCurrentSession().get(Product.class,id);

        if (product != null) {
            return product;
        }
        return null;
    }

    @Override
    public void add(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public void update(Product product) {
        sessionFactory.getCurrentSession().update(product);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);

        if(product != null) {
            session.delete(product);
        }
    }

    @Override
    public List<Product> getByCategory(Category category, Gender gender) {

            Query query = sessionFactory.getCurrentSession().getNamedQuery("Product.getByCategory");
            query.setParameter("gender", gender);
            query.setParameter("category", category);
            return (List<Product>) query.list();
    }

}
