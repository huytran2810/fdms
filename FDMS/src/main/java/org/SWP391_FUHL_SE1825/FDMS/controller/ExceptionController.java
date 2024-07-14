package org.SWP391_FUHL_SE1825.FDMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExceptionController {
    @GetMapping("/403")
    public String authenticationAxception() {
        return "403";
    }
    @PostMapping("/error")
    public String notFoundException() {
        return "403";
    }
}
