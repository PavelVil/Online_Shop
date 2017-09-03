package shop.dao;

import shop.model.*;

import java.util.List;
import java.util.Map;


public interface ShopDao {

    List<Gender> getGenders();

    Gender getGenderById(Long id);

    List<Category> getAllCategory();

    Category getCategoryById(Long id);

    Category getCategoryByName(String name);

    Gender getGenderByName(String name);

}
