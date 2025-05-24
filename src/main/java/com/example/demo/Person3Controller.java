package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Person3;
import com.example.demo.services.Person3Service;

import jakarta.validation.Valid;

@Controller
public class Person3Controller {
    
    @Autowired
    private Person3Service person3Service;

    @Autowired
	MessageSource messageSource;

    // Original form endpoint
    @GetMapping("/person3form")
    public String displayForm(Model model) {
        model.addAttribute("person", new Person3());
        return "new-form";
    }

    // Original form processing endpoint
    @PostMapping("/person3send")
    public String processForm(@Valid @ModelAttribute Person3 person3, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("person", person3);
            return "form";
        }else{
            person3Service.create(person3);
            return "redirect:/person3list";
        }
    }

    // New form endpoint for internationalization
    @GetMapping("/form")
    public String displayForm2(Model model) {
        model.addAttribute("person", new Person3());
        return "new-form2";
    }

    // New form processing endpoint for internationalization
    @PostMapping("/sendform2")
    public String processForm2(@Valid @ModelAttribute("person") Person3 person, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "new-form2";
        }
        person3Service.create(person);
        return "result2";
    }

    @GetMapping("/person3list")
    public String list(Model model) {
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
    
    @GetMapping("/newform2")
    public String displayNewForm2(Model model) {
        model.addAttribute("person", new Person3());
        return "new-form2";
    }

    @GetMapping("/show-message")
	@ResponseBody
	public String showMessage() {
		return  messageSource.getMessage("infoMessage", null, LocaleContextHolder.getLocale());
	}

	@GetMapping("/greet")
	@ResponseBody
	public String greet() {
		String[] name = new String[] { "Mark", "Taylor" };
		return messageSource.getMessage("greet", name, LocaleContextHolder.getLocale());
	}
}
