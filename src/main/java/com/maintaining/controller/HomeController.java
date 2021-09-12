package com.maintaining.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class HomeController {
    @RequestMapping("/")
    public String goHomePage() {
        return "redirect:swagger-ui.html";
    }
}
