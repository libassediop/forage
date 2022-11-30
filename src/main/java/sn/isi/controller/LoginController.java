package sn.isi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class LoginController {
    @PostMapping("/login")
    public String login(){
        return "redirect:/home";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("")
    public String logon(){
        return "index";
    }
}
