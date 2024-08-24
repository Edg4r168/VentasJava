package com.SysVentasJava.SysVentasJava.Controllers;

import com.SysVentasJava.SysVentasJava.Entities.Category;
import com.SysVentasJava.SysVentasJava.Entities.Product;
import com.SysVentasJava.SysVentasJava.Services.Interfaces.ICategoryService;
import com.SysVentasJava.SysVentasJava.Services.Interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService _productService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Product> products = _productService.findAllPaginated(pageable);
        model.addAttribute("products", products);

        int totalPage = products.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pageNumber = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("pageNumber", pageNumber);
        }
        return "products/index";
    }

    @PostMapping("/save")
    public String save(Product product, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute(product);
            attributes.addFlashAttribute("error",   "No se puede guardar debido a un error");
            return "products/create";
        }

        _productService.saveOrUpdate(product);
        attributes.addFlashAttribute("msg", "Producto creada correctamente");
        return "redirect:/products";
    }

    @GetMapping("/create")
    public String create(Product product){
        return "products/create";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Product product = _productService.findById(id).orElse(new Product());

        model.addAttribute("product",product);
        return "products/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, RedirectAttributes attributes){
        _productService.deleteById(id);
        attributes.addFlashAttribute("msg","Producto eliminada correctamente");

        return "redirect:/products";
    }

}
