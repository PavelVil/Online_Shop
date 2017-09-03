package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.model.*;
import shop.service.OrderService;
import shop.service.ProductService;
import shop.service.ShopService;
import shop.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/FashionShop")
public class ShopController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        setNecessaryAtr(model);
        return "index";
    }


    @RequestMapping(value = "/{gender}/{categoryId}", method = RequestMethod.GET)
    public String getProductsByCategory(@PathVariable("gender") String gender, @PathVariable("categoryId") Long id, Model model) {
        Gender g = shopService.getGenderByName(gender);
        Category c = shopService.getCategoryById(id);
        List<Product> products = productService.getByCategory(c, g);

        model.addAttribute("categoryList", shopService.getAllCategory());
        model.addAttribute("productByCategory", products);

        return "products";
    }


    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public String getProductById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.getById(id));
        setNecessaryAtr(model);

        return "product";
    }

    @RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] photo(@PathVariable("id")Long id) {
        return productService.getById(id).getPhoto();
    }



    @RequestMapping(value = "/addOrder", method = RequestMethod.GET)
    public String addOrder(HttpSession session) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        Order order = new Order();
        order.setUser(userService.getUserByName(userName));

        Map<Product,Integer> products = (Map<Product, Integer>) session.getAttribute("basket");

        orderService.addOrder(products,order);

        session.removeAttribute("basket");

        return "redirect:/";

    }


    @RequestMapping(value = "/deleteOrder/{id}", method = RequestMethod.GET)
    public String deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return "redirect:/";
    }



    @RequestMapping(value = "/putInCart", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String putInBasket( @RequestBody String productId, HttpSession session, Model model) {
        Long id = Long.parseLong(productId);
        Map<Product,Integer> products = addProductInCart(session, id);

        session.setAttribute("basket", products);

        setNecessaryAtr(model);
        model.addAttribute("genderList", shopService.getGenders());
        model.addAttribute("product", new Product());

        String count = String.valueOf(products.get(productService.getById(id)));

        return count;
    }

    @RequestMapping(value = "/deleteFromCart/{id}", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String deleteFromBasket(@RequestBody String productId,Model model, HttpSession session){

        Long id = Long.parseLong(productId);

        Map<Product, Integer> products = deleteProductFromCart(session, id);
        session.setAttribute("basket", products);
        model.addAttribute("products", products);
        setNecessaryAtr(model);

        String count = String.valueOf(products.get(productService.getById(id)));

        return count;
    }


    @RequestMapping(value = "/shopCart", method = RequestMethod.GET)
    public String basket(Model model, HttpSession session) {
        Map<Product, Integer> products = null;
        if (session.getAttribute("basket")!=null) {
            products = (Map<Product,Integer>) session.getAttribute("basket");
            model.addAttribute("products", products);
        }

        setNecessaryAtr(model);

        return "shopCart";
    }

    @RequestMapping(value = "/addProductToCart/{id}", method = RequestMethod.GET)
    public String addProductToCart(@PathVariable("id") Long id ,HttpSession session, Model model) {
        Map<Product,Integer> products = addProductInCart(session, id);
        session.setAttribute("basket", products);
        setNecessaryAtr(model);

        return "redirect:/FashionShop/shopCart ";
    }


    @RequestMapping(value = "/removeFromCart", method = RequestMethod.GET)
    public String removeFromCart(HttpSession session) {
        if (session.getAttribute("basket")!=null) {
            session.removeAttribute("basket");
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Model model) {
        setNecessaryAtr(model);
        model.addAttribute("emailModel", new Email());

        return "contact";
    }


    private Map<Product, Integer> deleteProductFromCart(HttpSession session, Long productId) {

        Map<Product, Integer> products = null;

        Product product = productService.getById(productId);

        if (session.getAttribute("basket")!=null) {

            products = (Map<Product,Integer>) session.getAttribute("basket");

            for(Iterator<Map.Entry<Product, Integer>> iterator = products.entrySet().iterator(); iterator.hasNext();) {
                Map.Entry<Product,Integer> entry = iterator.next();
                if (entry.getKey().equals(product) && entry.getValue() <= 1) {
                    iterator.remove();
                } else if (entry.getKey().equals(product) && entry.getValue() > 1) {
                    products.put(entry.getKey(),products.get(product)-1);
                }
            }
        }

        return products;
    }

    private Map<Product, Integer> addProductInCart(HttpSession session, Long productId) {
        Map<Product, Integer> products = null;

        Product product = productService.getById(productId);

        if (session.getAttribute("basket")!=null) {
            products = (Map<Product,Integer>) session.getAttribute("basket");
        } else {
            products = new HashMap<>();
        }

        if(products.containsKey(product)) {
            products.put(product,products.get(product)+1);
        } else {
            products.put(product,1);
        }

        return products;
    }

    private void setNecessaryAtr(Model model) {
        model.addAttribute("productMenList", productService.getAllByGender(Gender.GenderEnum.MEN));
        model.addAttribute("productWomenList", productService.getAllByGender(Gender.GenderEnum.WOMEN));
        model.addAttribute("categoryList", shopService.getAllCategory());
    }



}
