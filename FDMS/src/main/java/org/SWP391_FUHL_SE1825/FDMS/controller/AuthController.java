package org.SWP391_FUHL_SE1825.FDMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("")
public class AuthController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/auth")
    public String auth() {return "test_auth";}
    @GetMapping("/public-api/profile")
    public String profile() {return "profile";}
    @GetMapping("/public-api/students")
    public String students() {return "students";}

}
