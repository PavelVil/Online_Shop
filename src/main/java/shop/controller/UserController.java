package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import shop.model.Gender;
import shop.model.User;
import shop.service.ProductService;
import shop.service.ShopService;
import shop.service.UserService;

@Controller
@RequestMapping(value = "/FashionShop")
public class UserController {


    @Autowired
    private ProductService productService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("productMenList", productService.getAllByGender(Gender.GenderEnum.MEN));
        model.addAttribute("productWomenList", productService.getAllByGender(Gender.GenderEnum.WOMEN));
        model.addAttribute("categoryList", shopService.getAllCategory());

        return "account";
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("productMenList", productService.getAllByGender(Gender.GenderEnum.MEN));
        model.addAttribute("productWomenList", productService.getAllByGender(Gender.GenderEnum.WOMEN));
        model.addAttribute("categoryList", shopService.getAllCategory());

        return "register";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestParam("role")String role, @ModelAttribute("user") User user) {
        userService.addUser(user);
        userService.saveRole(user, role);
        return "redirect:/";
    }

}
