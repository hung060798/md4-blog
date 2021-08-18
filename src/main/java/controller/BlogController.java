package controller;

import model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import service.IBlogService;

import java.util.ArrayList;

@Controller
public class BlogController {
    ArrayList<Blog> list = new ArrayList<>();

    @Autowired
    IBlogService iBlogService;

    @GetMapping("/home")
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("home");
        list = iBlogService.fillAll();
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveBlog(@ModelAttribute("blog") Blog blog) {
        iBlogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("blog", new Blog());
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/show/{index}")
    public ModelAndView showBlog(@PathVariable int index) {
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("blog", list.get(index));
        return modelAndView;
    }

    @GetMapping("/edit/{index}")
    public ModelAndView showEdit(@PathVariable int index) {
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("blog", list.get(index));
        return modelAndView;
    }

    @PostMapping("/edit/{index}")
    public ModelAndView edit(@ModelAttribute Blog blog) {
        iBlogService.edit(blog);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/delete/{index}")
    public ModelAndView delete(@PathVariable int index) {
        iBlogService.delete(list.get(index));
        return new ModelAndView("redirect:/home");
    }

}