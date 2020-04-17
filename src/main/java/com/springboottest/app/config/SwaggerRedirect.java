package com.springboottest.app.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerRedirect {

    @GetMapping(value = "/")
    public String index() {
        return "redirect:swagger-ui.html";
    }
}
