package me.bnnq.homework.Controllers;

import me.bnnq.homework.Models.Image;
import me.bnnq.homework.Models.Post;
import me.bnnq.homework.Repositories.IImageRepository;
import me.bnnq.homework.Repositories.IPostRepository;
import me.bnnq.homework.Utils.Resources;
import me.bnnq.homework.Utils.Views;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class PostController
{
    private final IPostRepository postRepository;
    private final IImageRepository imageRepository;

    public PostController(IPostRepository postRepository, IImageRepository imageRepository)
    {
        this.postRepository = postRepository;
        this.imageRepository = imageRepository;
    }

    @GetMapping("/post/{id}")
    public String getPost(Model model, @PathVariable(name = "id") Long id)
    {
        var post = postRepository.findById(id).orElseThrow();
        model.addAttribute("post", post);
        return Views.getView(model, "post");
    }

    @GetMapping("/admin/post/list")
    public String getPosts(Model model, @RequestParam(name = "page", defaultValue = "0") int page)
    {
        var posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return Views.getView(model, "posts");
    }

    @GetMapping("/admin/post/new")
    public String createPost(Model model)
    {
        return Views.getView(model, "createPost");
    }

    @GetMapping("/admin/post/edit/{id}")
    public String editPost(Model model, @PathVariable(name = "id") Long id)
    {
        var post = postRepository.findById(id).orElseThrow();
        model.addAttribute("post", post);
        return Views.getView(model, "editPost");
    }

    @GetMapping("/admin/post/delete/{id}")
    public String deletePost(@PathVariable(name = "id") Long id)
    {
        var post = postRepository.findById(id).orElseThrow();
        deletePostImages(post);
        postRepository.delete(post);
        return "redirect:/admin/post/list";
    }

    @PostMapping("/admin/post/new")
    public String createPost(@RequestParam(name = "header") String header, @RequestParam(name = "content") String content, @RequestParam("images") MultipartFile[] images)
    {
        var post = new Post();
        post.setHeader(header);
        post.setContent(content);

        savePostImages(post, images);

        postRepository.save(post);
        return "redirect:/admin/post/list";
    }

    @PostMapping("/admin/post/edit")
    public String editPost(@RequestParam(name = "id") Long id, @RequestParam(name = "header") String header, @RequestParam(name = "content") String content, @RequestParam(value = "images", required = false) MultipartFile[] images)
    {
        var post = postRepository.findById(id).orElseThrow();
        post.setHeader(header);
        post.setContent(content);

        if (images != null)
        {
            //Remove previous images
            deletePostImages(post);

            //Add new images
            savePostImages(post, images);
        }

        postRepository.save(post);

        return "redirect:/admin/post/list";
    }

    private void deletePostImages(Post post)
    {
        try
        {
            Iterator<Image> iterator = post.getImages().iterator();
            while (iterator.hasNext())
            {
                Image image = iterator.next();
                Files.delete(Path.of(Resources.getImageUploadDirectory(), Paths.get(image.getPath()).getFileName().toString()));
                iterator.remove();  // Remove the image from the post
                imageRepository.deleteById(image.getId());
            }
        }
        catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
    }

    private void savePostImages(Post post, MultipartFile[] images)
    {
        List<Image> imageList = new ArrayList<>();
        for (MultipartFile imageFile : images)
        {
            var image = new Image(null, Resources.saveImage(imageFile), post);
            imageList.add(image);
        }
        post.setImages(imageList);
    }

}