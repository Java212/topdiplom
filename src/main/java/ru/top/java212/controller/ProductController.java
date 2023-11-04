package ru.top.java212.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.entity.Product;
import ru.top.java212.entity.Profile;
import ru.top.java212.entity.User;
import ru.top.java212.repository.ProductRepository;
import ru.top.java212.repository.ProfileRepository;
import ru.top.java212.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;

    @GetMapping("/search-product")
    public ModelAndView searchProduct(Model model, @Param("title") String title) {
        ModelAndView mv = new ModelAndView("categories");
        mv.addObject("products", productService.getProductsByTitleNotBusy(false, title));
        return mv;
    }

    @PostMapping("/create-product")
    public String createProduct(Model model, @ModelAttribute Product product) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (principal instanceof User) ? ((User) principal) : new User();
        Profile profile = profileRepository.findByUser(user);
        product.setProfile(profile);
        productService.saveProduct(product);
        return "redirect:personal-account/tools";
    }
    @GetMapping("/delete/product/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, Model model) {
        // Вызываем метод сервиса для удаления продукта
        boolean deleted = productService.delete(id);
        if (deleted) {
            // Если продукт успешно удален, можно выполнить какие-то дополнительные действия
            // Например, обновить модель с данными для представления
            model.addText("message");
        } else {
            // Если продукт с указанным id не был найден, можно добавить соответствующее сообщение об ошибке
            model.addText("error");
        }
        return "redirect:/personal-account/tools";
    }

}

