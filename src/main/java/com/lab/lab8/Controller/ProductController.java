package com.lab.lab8.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.lab.lab8.Entitys.Product;
import com.lab.lab8.Service.ProductService;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProductController {
    // private final ProductService productService;

    // public ProductController(ProductService productService){
    //     this.productService = productService;
    // }

    @GetMapping("/")
    public String ListPage() {
        return "products/list";
    }

    @GetMapping("/products/add")
    public String AddPage(Model model) {
        model.addAttribute("product", "Hello");
        return "products/add";
    }
    
    
}
