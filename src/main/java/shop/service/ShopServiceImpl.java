package shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.ShopDao;
import shop.model.Category;
import shop.model.Gender;

import java.util.List;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {

    private ShopDao shopDao;

    public void setShopDao(ShopDao shopDao) {
        this.shopDao = shopDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Gender> getGenders() {
        return shopDao.getGenders();
    }

    @Override
    public Gender getGenderById(Long id) {
        return shopDao.getGenderById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllCategory() {
        return shopDao.getAllCategory();
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategoryByName(String name) {
        return shopDao.getCategoryByName(name);
    }

    @Override
    public Category getCategoryById(Long id) {
        return shopDao.getCategoryById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Gender getGenderByName(String name) {
        return shopDao.getGenderByName(name);
    }
}
