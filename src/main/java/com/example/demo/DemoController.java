package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Person;
import com.example.demo.model.Person2;
import com.example.demo.model.PersonApplication;
import com.example.demo.model.PersonFormData;
import com.example.demo.services.DemoService;
import com.example.demo.validator.AgeValidator;



import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;






@Controller
public class DemoController {

    private final DemoApplication demoApplication;
    @Autowired
	private AgeValidator ageValidator;

    @Autowired
	private DemoService service;


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


    @GetMapping("/appForm")
    public String showApplicationForm(Model model) {
        model.addAttribute("personApplication", new PersonApplication());
        return "applicationform";
    }

    @PostMapping("/send")
    public String processForm(@Valid @ModelAttribute PersonApplication personApplication, BindingResult result, Model model, RedirectAttributes attributes) {
        
        ageValidator.validate(personApplication, result);

        if(result.hasErrors()){
            model.addAttribute("personApplication", personApplication);
            return "applicationform";
        }else{
            attributes.addFlashAttribute("person",new Person(personApplication));
            return "redirect:/subresult";
        }
    }
    @PostMapping("/sendnewform")
    public String processNewForm(@Valid @ModelAttribute PersonFormData personFormData, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("personFormData", new PersonFormData());
            return "newform";
        }else{
            Person2 person = new Person2(personFormData);
            service.create(person);
            return "redirect:/list";
        }
    };
    

    @GetMapping("/subresult")
    public String displayApplicationResult(Model model) {
        if(!model.containsAttribute("person"))
            return "redirect:/applicationform"; 
        return  "submission-result";
    }
    
    @GetMapping("/newform")
    public String displayNewForm(Model model) {
        model.addAttribute("personFormData", new PersonFormData());
        return "new-form";
    }
    
    
    @GetMapping("/list")
    public String displayPersonList(Model model) {
        model.addAttribute("persons", service.findAll());
        return "person-list";
    }
    

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id, Model model) {
        service.delete(id);
        model.addAttribute("persons", service.findAll());
        return "person-list";
    }
    
    

    
    
}
