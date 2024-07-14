package org.SWP391_FUHL_SE1825.FDMS.controller;

import org.SWP391_FUHL_SE1825.FDMS.entity.ManagerSecondaryInformation;
import org.SWP391_FUHL_SE1825.FDMS.entity.StudentSecondaryInformation;
import org.SWP391_FUHL_SE1825.FDMS.entity.UserEntity;
import org.SWP391_FUHL_SE1825.FDMS.repository.ManagerRepository;
import org.SWP391_FUHL_SE1825.FDMS.repository.UserRepository;
import org.SWP391_FUHL_SE1825.FDMS.security.SecurityUtil;
import org.SWP391_FUHL_SE1825.FDMS.service.IUserService;
import org.SWP391_FUHL_SE1825.FDMS.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;
//    @Autowired
//    private AdminServiceImpl adminServiceIml;

    //list user
    @GetMapping("/user")
    public String getAllUser(@RequestParam(value = "page", defaultValue = "0") int page,Model model) {
        Page<ManagerSecondaryInformation> userpage = userServiceImpl.list(PageRequest.of(page, 10));
        model.addAttribute("user", userpage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userpage.getTotalPages());
        return "users";
    }

    //update user
    @GetMapping("/user/{userId}/update")
    public String updateUser(@PathVariable("userId") Long userId,Model model) {
        UserEntity user = userServiceImpl.getOneUser(userId);
        model.addAttribute("user", user);
        return "users";
    }

    @GetMapping("/user/{userId}")
    public String userDetail(@PathVariable("userId") Long userId,Model model) {
        UserEntity user = userServiceImpl.getOneUser(userId);
        model.addAttribute("user", user);
        return "user-detail";
    }

    @GetMapping("/user/{userId}/active")
    public String activeUser(@PathVariable("userId") Long userId) {
        UserEntity user = userServiceImpl.getOneUser(userId);
        user.setStatus("active");
        userRepository.save(user);

        return "redirect:/user";
    }
    @GetMapping("/user/search")
    public String searchUser(@RequestParam(value = "query") String query,@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
        query = query.trim();
        Page<UserEntity> userpage = userServiceImpl.searchUser(query,PageRequest.of(page, 10));
        model.addAttribute("user", userpage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userpage.getTotalPages());
        return "users";
    }

    @GetMapping("/user/profile")
    public String profile(Model model) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUserName(username);
        model.addAttribute("user", user);
        return "profile";
    }

//    @PostMapping("user/profile")
//    public String profile(@ModelAttribute("user") UserEntity user1) {
//        String username = SecurityUtil.getSessionUser();
//        UserEntity user = userRepository.findByUserName(username);
//        userServiceImpl.maptoUser(user1,user);
//        return "redirect:/home";
//    }
}
