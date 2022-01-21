package com.infoshare.myfitwebapp.controller;

import com.infoshare.myfitwebapp.model.ProductData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ProductController {

    @GetMapping("user/products/new")
    public String getProduct(Model model) {
        model.addAttribute("product", new ProductData());
        return "products-new";
    }

    @PostMapping("user/products/new")
    public String addProduct(@Valid @ModelAttribute("product") ProductData product, Errors errors) {
        if (errors.hasErrors()) {
            return "products-new";
        }

       /* TODO
            service.add(product);*/

        return "redirect:/";
    }

}