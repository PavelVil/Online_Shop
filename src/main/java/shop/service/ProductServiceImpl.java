package shop.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.ProductDao;
import shop.model.Category;
import shop.model.Gender;
import shop.model.Product;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllByGender(Gender.GenderEnum gender) {
        return productDao.getAllByGender(gender);
    }

    @Override
    @Transactional(readOnly = true)
    public Product getById(Long id) {
        return productDao.getById(id);
    }

    @Override
    public void add(Product product) {
        productDao.add(product);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void delete(Long id) {
        productDao.delete(id);
    }

    @Override
    public List<Product> getByCategory(Category category, Gender gender) {
        return productDao.getByCategory(category, gender);
    }
}
