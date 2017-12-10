package news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import news.domain.Writer;
import news.repository.WriterRepository;

@Controller
public class WriterController {

    @Autowired
    WriterRepository writerRepository;

    @GetMapping("/writer")
    public String list(Model model) {
        model.addAttribute("writers", writerRepository.findAll());

        return "writer";
    }

    @PostMapping("/writer")
    public String addAuthor(@RequestParam String name) {
        Writer writer = new Writer(name);
        writerRepository.save(writer);
        return "redirect:/writer";
    }

}