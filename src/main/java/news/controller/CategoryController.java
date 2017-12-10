/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import news.domain.Category;
import news.repository.CategoryRepository;
import news.repository.NewsItemRepository;

@Controller
public class CategoryController {

    @Autowired
    private NewsItemRepository newsItemRepository;
    
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public String list(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());

        return "category";
    }

    @PostMapping("/categories")
    public String addCategory(@RequestParam String name) {
        Category category = new Category(name);
        categoryRepository.save(category);
        return "redirect:/categories";
    }

}