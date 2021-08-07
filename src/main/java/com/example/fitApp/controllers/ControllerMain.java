package com.example.fitApp.controllers;

import com.example.fitApp.models.Models;
import com.example.fitApp.repo.ModelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerMain {

    private final ModelsRepository modelsRepository;

    @Autowired
    public ControllerMain(ModelsRepository modelsRepository) {
        this.modelsRepository = modelsRepository;
    }

    @GetMapping("/")
    public String home( Model model) {
        if (model == null) {
            throw new RuntimeException("Model cannot be null");
        }

        model.addAttribute("title", "главная страница");
        return "home";
    }
    @PostMapping
    public String Posthome(@RequestParam String exercise, @RequestParam int approaches, @RequestParam double time, Model model) {
        Models models = new Models(exercise, approaches, time);
        modelsRepository.save(models);
        return "redirect:/history";
    }
    @GetMapping("/history")
    public String history(Model model) {
        Iterable<Models> posts = modelsRepository.findAll();
        model.addAttribute("posts", posts);
        return "history";
    }
    @PostMapping("history/{id}/remove")
    public String delete(Model model,@PathVariable(value = "id") long id){

       Models models =  modelsRepository.findById(id).orElseThrow();
       modelsRepository.delete(models);
        return "redirect:/history";
    }

}
