package news.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import news.domain.Category;
import news.domain.FileObject;
import news.domain.NewsItem;
import news.domain.Writer;
import news.repository.CategoryRepository;
import news.repository.FileObjectRepository;
import news.repository.NewsItemRepository;
import news.repository.WriterRepository;
import news.service.FileService;
import news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class NewsItemController {

    @Autowired
    private NewsItemRepository newsItemRepository;
    @Autowired
    private WriterRepository writerRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FileObjectRepository fileObjectRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private NewsService newsService;

    @GetMapping("/")
    public String home(Model model) {
        return "redirect:/news";
    }
    
    @GetMapping("/news")
    public String news(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("newsItems", newsItemRepository.findAll());
        return "news";
    }
    

    @GetMapping(path = "/pictures/{id}/content", produces = "image/png")
    @ResponseBody
    public byte[] get(@PathVariable Long id) {
        return newsItemRepository.getOne(id).getPicture().getContent();
    }

    @GetMapping("/new")
    public String showNewsItemForm(Model model) {
        model.addAttribute("writers", writerRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "new";
    }

    @GetMapping("/news/{id}")
    public String showNewsItem(@PathVariable Long id, Model model) {
        NewsItem newsItem = newsItemRepository.getOne(id);
        System.out.println(newsItem.getTopic());
        model.addAttribute("newsItem", newsItem);
        return "newsItem";
    }

    @PostMapping("/new")
    public String addNews(@RequestParam String topic,
            @RequestParam String ingres, @RequestParam String text,
            @RequestParam(value = "writers[]") String[] writers,
            @RequestParam(value = "categories[]") String[] categories,
            @RequestParam("file") MultipartFile file) throws IOException {

        NewsItem newsItem = new NewsItem();
        newsItem.setTopic(topic);
        newsItem.setIngres(ingres);
        newsItem.setText(text);

        List<Writer> writerList = newsService.createAuthorList(writers);
        List<Category> categoryList = newsService.createCategoryList(categories);

        newsItem.setWriters(writerList);
        newsItem.setCategories(categoryList);
        newsItem.setPicture(fileService.create(file));

        newsItemRepository.save(newsItem);
        newsService.assignNewsItemToAuthors(newsItem, writerList);
        newsService.assignNewsItemToCategories(newsItem, categoryList);

        return "redirect:/";
    }

}
