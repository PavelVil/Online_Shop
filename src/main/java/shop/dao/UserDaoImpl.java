package shop.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import shop.model.Role;
import shop.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    @Override
    public User getUserByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where userName = :name").setString("name", name);
        return (User) query.uniqueResult();
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void deleteUser(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where userName=:name").setString("name",name);
        User user = (User)query.uniqueResult();
        if (user!=null) {
            session.delete(user);
        }
    }

    @Override
    public void saveRole(User user, String roleName) {
        if (user != null && roleName != null) {
            Role role = new Role(user);
            role.setAuthority(roleName);
            sessionFactory.getCurrentSession().save(role);
        }
    }
}
