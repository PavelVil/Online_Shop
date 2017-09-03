package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import shop.model.Email;
import shop.service.EmailService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/FashionShop")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/email/send", method = RequestMethod.POST)
    public ModelAndView email(@ModelAttribute("emailModel") Email emailModel) {
        System.out.println("EmailController email is called");
        Map<String, Object> model = new HashMap<>();
        model.put("from", emailModel.getEmail());
        model.put("subject", "Сообщение от " + emailModel.getName() + "!");
        model.put("to", "forspringshop@gmail.com");
        model.put("ccList", new ArrayList<>());
        model.put("bccList", new ArrayList<>());
        model.put("message", emailModel.getMessage());
        boolean result = emailService.sendEmail("registered.vm", model);

        return new ModelAndView("redirect:/FashionShop/contact", "resultSending", result);
    }

}
