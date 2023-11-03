package me.bnnq.homework.Controllers;

import me.bnnq.homework.Utils.Views;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController
{
    @GetMapping("/")
    public String getHome(@RequestParam(name="text", required = false, defaultValue = "Hello Spring!") String text, Model model)
    {
        model.addAttribute("text", text);
        return Views.getView(model, "home");
    }

    @GetMapping("/about")
    public String getAbout(Model model)
    {
        return Views.getView(model, "about");
    }

    @GetMapping("/contacts")
    public String getContacts(Model model)
    {
        return Views.getView(model, "contacts");
    }
}