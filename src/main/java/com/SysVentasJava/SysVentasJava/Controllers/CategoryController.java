package com.SysVentasJava.SysVentasJava.Controllers;

import com.SysVentasJava.SysVentasJava.Entities.Category;
import com.SysVentasJava.SysVentasJava.Services.Interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.PageRequest;
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
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService _categoryService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(4);
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Category> categories = _categoryService.findAllPaginated(pageable);
        model.addAttribute("categories", categories);

        int totalPage = categories.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pageNumber = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("pageNumber", pageNumber);
        }
        return "categories/index";
    }

    @PostMapping("/save")
    public String save(Category category, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute(category);
            attributes.addFlashAttribute("error",   "No se puede guardar debido a un error");
            return "categories/create";
        }

        _categoryService.saveOrUpdate(category);
        attributes.addFlashAttribute("msg", "Categoria creada correctamente");
        return "redirect:/categories";
    }

    @GetMapping("/create")
    public String create(Category category){
        return "categories/create";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Category category = _categoryService.findById(id).get();
        model.addAttribute("category",category);
        return "categories/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, RedirectAttributes attributes){
        Category category = _categoryService.findById(id).get();

        _categoryService.deleteById(category.getId());
        attributes.addFlashAttribute("msg","Categoria eliminada correctamente");

        return "redirect:/categories";
    }
}
