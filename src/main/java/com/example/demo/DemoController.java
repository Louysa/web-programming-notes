package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
    @GetMapping("")
    @ResponseBody
    public String requestHandler(@RequestParam(value = "isim",required=false) String val) {
        return val;
    }
}
