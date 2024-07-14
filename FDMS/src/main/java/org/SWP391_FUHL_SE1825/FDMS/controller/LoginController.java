package org.SWP391_FUHL_SE1825.FDMS.controller;

import org.SWP391_FUHL_SE1825.FDMS.entity.UserEntity;
import org.SWP391_FUHL_SE1825.FDMS.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/login")
public class LoginController {
    IUserService userService;

    @Autowired
    public LoginController(IUserService userService) {
        this.userService = userService;
    }
    @PostMapping()
    String login(@RequestBody String username, @RequestBody String password, Model model){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        String message = "Password or Username invalid";
        UserEntity userEntity = userService.getUser(username);
        if (userEntity!=null){
            if(passwordEncoder.matches(password,userEntity.getPassword())){
                return "home";
            }
            model.addAttribute("error",message);
            return "login";
        }
        model.addAttribute("error",message);
        return "login";
    }
}
