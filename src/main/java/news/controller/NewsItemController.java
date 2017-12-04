/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.controller;

import news.repository.CategoryRepository;
import news.repository.NewsItemRepository;
import news.repository.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsItemController {
    @Autowired
    private NewsItemRepository newsItemRepository;
    @Autowired
    private WriterRepository writerRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("newsItems", newsItemRepository.findAll());
        return "news";
    }
    
    
}
