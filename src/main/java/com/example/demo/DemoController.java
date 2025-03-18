package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DemoController {

    private final DemoApplication demoApplication;

    DemoController(DemoApplication demoApplication) {
        this.demoApplication = demoApplication;
    }
    @GetMapping("/show")
    public String showView() {
        return "view-1" ;
    }

    @GetMapping("/modeltest")
    public String modeltest(Model model) {
        model.addAttribute("attrib1","Value 1 from ModelTest");
        model.addAttribute("attrib2","Value 2 from ModelTest");
        return "result" ;
    }

    @GetMapping("/modelmaptest")
    public String modelmaptest(ModelMap modelMap){
        modelMap.addAttribute("attrib1", "Value 1 From ModelMapTest");
        modelMap.addAttribute("attrib2", "Value 2 From ModelMapTest");
        return "result";
    }

    @GetMapping("/modelandviewtest")
    public ModelAndView modelandviewtest(){
        ModelAndView mv = new ModelAndView("result");

        mv.addObject("attrib1", "Value 1 From ModelAndViewTest");
        mv.addObject("attrib2", "Value 2 From ModelAndViewTest");
        return mv;
    }
    
    
    @GetMapping({"/display-form","/form.html" })
    public String displayForm(Model model){
        model.addAttribute("person", new Person());
        return "form";

    }
    
    @PostMapping("/send-values")
    public String sendForm(@ModelAttribute Person person, Model model) {
        model.addAttribute("person", person);
        return "personresult";
    }
    
    @PostMapping("/send-values-redirect")
    public String sendFormRedirect(@ModelAttribute Person person, RedirectAttributes attributes){
        attributes.addFlashAttribute("person",person);
        return "redirect:/result";
    }

    @GetMapping("/result")
    public String showResult(Model model){

        if(!model.containsAttribute("person")){
            return "redirect:/form.html";
        }
        return "personresult";
    }
}
