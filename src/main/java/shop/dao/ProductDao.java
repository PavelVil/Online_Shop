package shop.dao;

import shop.model.Category;
import shop.model.Gender;
import shop.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getAll();

    List<Product> getAllByGender(Gender.GenderEnum gender);

    Product getById(Long id);

    void add(Product product);

    void update(Product product);

    void delete(Long id);

    List<Product> getByCategory(Category category, Gender gender);

}
