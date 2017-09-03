package shop.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shop.model.Order;
import shop.model.Product;
import shop.model.User;
import shop.service.OrderService;
import shop.service.ProductService;
import shop.service.ShopService;
import shop.service.UserService;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping(value = "/FashionShop/admin")
@PreAuthorize("isAuthenticated()")
public class AdminController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainAdminPage(Model model) {
        model.addAttribute("orderList", orderService.getAllOrders());

        return "/admin/index";
    }

    @RequestMapping(value = "/main/detailed/{id}", method = RequestMethod.GET)
    public String detailedAboutOrder(@PathVariable("id") Long id, Model model){
        Order order = orderService.getById(id);

        if(order!=null) {
            model.addAttribute("order", order);
            model.addAttribute("details", order.getDetails());
        }
        model.addAttribute("orderList", orderService.getAllOrders());

        return "/admin/index";
    }

    @RequestMapping(value = "/main/deleteOrder/{id}", method = RequestMethod.GET)
    public String deleteOrder(@PathVariable("id") Long id) {
            orderService.deleteOrder(id);

            return "redirect:/FashionShop/admin/main";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String allUsers(Model model) {
        model.addAttribute("userList", userService.getAllUsers());

        return "/admin/users";
    }

    @RequestMapping(value = "/users/delete/{username}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);

        return "redirect:/FashionShop/admin/users";
    }

    @RequestMapping(value = "/users/update/{username}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("username") String username, Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("user", userService.getUserByName(username));

        return "/admin/users";
    }

    @RequestMapping(value = "/users/updateInfo", method = RequestMethod.POST)
    public String updateUserInfo(@RequestParam("role") String role, @ModelAttribute("user")User user) {
        userService.saveRole(user, role);
        userService.updateUser(user);
        return "redirect:/FashionShop/admin/users";
    }

    @RequestMapping(value = "/users/available/{userName}",method = RequestMethod.GET)
    public String updateAvailable(@PathVariable("userName") String userName){
        User user = userService.getUserByName(userName);
        if(user!=null) {
            if (user.isEnabled()) {
                user.setEnabled(false);
            } else {
                user.setEnabled(true);
            }
            userService.updateUser(user);
        }
        return "redirect:/FashionShop/admin/users";
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String product(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categoryList", shopService.getAllCategory());
        model.addAttribute("genderList", shopService.getGenders());

        return "/admin/product";
    }

    @RequestMapping(value = "/product/addProduct", method = RequestMethod.POST, headers = "content-type=multipart/*")
    public String addProduct(@RequestParam("categoryOnForm") String categoryName, @RequestParam("genderOnForm") String gender,
                             @ModelAttribute("product") Product product, @RequestParam("file")MultipartFile file) {

        if (file != null) {
            setPhoto(file, product);
        }

        product.setCategory(shopService.getCategoryByName(categoryName));
        product.setGender(shopService.getGenderByName(gender));

        if (product.getProductId() != null) {
            productService.update(product);
        } else {
            productService.add(product);
        }

        return "redirect:/FashionShop/admin/product";
    }

    @RequestMapping(value = "/updateProduct/{id}", method = RequestMethod.GET)
    public String updateProduct(@PathVariable("id") Long id ,Model model) {

        model.addAttribute("product", productService.getById(id));

        return "/admin/product";

    }

    @RequestMapping(value = "/deleteProduct/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.delete(id);

        return "redirect:/FashionShop/main";
    }

    private void setPhoto(MultipartFile file, Product product){
        byte[] fileContent = null;
        try {
            InputStream inputStream = file.getInputStream();
            fileContent = IOUtils.toByteArray(inputStream);
            product.setPhoto(fileContent);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
