package com.example.flowsubject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    //View 컨트롤러
    @RequestMapping(value="/extension")
    public String Extension(){
        return "ExtensionView.html";
    }

}
