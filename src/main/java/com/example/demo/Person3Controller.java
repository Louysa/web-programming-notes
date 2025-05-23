package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.model.Person3;
import com.example.demo.services.Person3Service;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class Person3Controller {
    

    @Autowired
    private Person3Service person3Service;

    @GetMapping("/person3form")
    public String displayForm(Model model) {
        model.addAttribute("person",new Person3());
        return "new-form";
    }

    @PostMapping("/person3send")
    public String processForm(@Valid @ModelAttribute Person3 person3, BindingResult bindingResult,Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("person", person3);
            return "form";
        }else{
            person3Service.create(person3);
            return "redirect:/person3list";
        }
    }

    @GetMapping("/person3list")
    public String list( Model model){
        model.addAttribute("persons", person3Service.findAll());
        return "person-list";
    }

    @GetMapping("/findp3/{name}")
    public String listByName(@PathVariable String name, Model model) {  
        model.addAttribute("persons", person3Service.findByLastName(name));
        return "person-list";
    }
    
    @PostMapping("/deletep3/{id}")
    public String delete(@PathVariable long id){
        person3Service.delete(id);
        return "redirect:/list";
    }
    
    

}
