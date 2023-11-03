package me.bnnq.homework.Controllers;

import me.bnnq.homework.Models.Post;
import me.bnnq.homework.Repositories.IPostRepository;
import me.bnnq.homework.Utils.Views;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController
{
    private final IPostRepository postRepository;

    public BlogController(IPostRepository postRepository)
    {
        this.postRepository = postRepository;
    }

    @GetMapping("/blog")
    public String getBlog(Model model)
    {
        Iterable<Post> iterable = postRepository.findAll();
        model.addAttribute("posts", iterable);
        return Views.getView(model, "blog");
    }

}
