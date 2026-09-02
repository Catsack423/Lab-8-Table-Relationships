package com.lab.lab8.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.lab.lab8.Dto.ProductDto;
import com.lab.lab8.Entitys.Product;
import com.lab.lab8.Service.ProductService;
import com.lab.lab8.exception.ProductNotFoundException;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String listPage(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products/list";
    }

    @GetMapping("/products/add")
    public String addPage(Model model) {
        model.addAttribute("product", new ProductDto());
        return "products/add";
    }

    @PostMapping("/products/save")
    public String save(@Valid ProductDto productDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("product", productDto);
            return "products/add";
        }
        productService.save(productDto.toEntity());
        model.addAttribute("message", "บันทึกสินค้าสำเร็จ!");
        model.addAttribute("products", productService.findAll());
        return "products/list";
    }

    @GetMapping("/products/edit/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        model.addAttribute("product", ProductDto.fromEntity(product));
        return "products/edit";
    }

    @PostMapping("/products/update/{id}")
    public String update(@PathVariable Long id, @Valid ProductDto productDto,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            productDto.setId(id);
            model.addAttribute("product", productDto);
            return "products/edit";
        }
        productService.update(id, productDto.toEntity());
        model.addAttribute("message", "แก้ไขสินค้าสำเร็จ!");
        model.addAttribute("products", productService.findAll());
        return "products/list";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteConfirm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        model.addAttribute("product", product);
        return "products/delete";
    }

    @PostMapping("/products/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        productService.deleteById(id);
        model.addAttribute("message", "ลบสินค้าสำเร็จ!");
        model.addAttribute("products", productService.findAll());
        return "products/list";
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public String handleProductNotFound(ProductNotFoundException exception,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", exception.getMessage());
        return "redirect:/products";
    }
}
