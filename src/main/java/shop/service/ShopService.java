package shop.service;


import shop.model.Category;
import shop.model.Gender;

import java.util.List;

public interface ShopService {

    List<Gender> getGenders();

    Gender getGenderById(Long id);

    List<Category> getAllCategory();

    Category getCategoryByName(String name);

    Category getCategoryById(Long id);

    Gender getGenderByName(String name);

}
