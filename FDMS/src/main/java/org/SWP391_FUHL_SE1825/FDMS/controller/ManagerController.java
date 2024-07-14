package org.SWP391_FUHL_SE1825.FDMS.controller;

import org.SWP391_FUHL_SE1825.FDMS.dto.ManagerDTO;
import org.SWP391_FUHL_SE1825.FDMS.entity.ManagerSecondaryInformation;
import org.SWP391_FUHL_SE1825.FDMS.entity.Payment;
import org.SWP391_FUHL_SE1825.FDMS.entity.StudentSecondaryInformation;
import org.SWP391_FUHL_SE1825.FDMS.entity.UserEntity;
import org.SWP391_FUHL_SE1825.FDMS.repository.ManagerRepository;
import org.SWP391_FUHL_SE1825.FDMS.repository.UserRepository;
import org.SWP391_FUHL_SE1825.FDMS.service.Impl.ManagerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ManagerController {
    @Autowired
    private ManagerServiceImpl managerService;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);

    @GetMapping("/admin/manager-list")
    public String getAllManager(@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
        Page<ManagerDTO> managerpage = managerService.getAllManagers(PageRequest.of(page, 10));
        model.addAttribute("manager", managerpage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", managerpage.getTotalPages());
        return "manager-list";
    }

    @GetMapping("admin/manager/search")
    public String searchManager(@RequestParam(value = "query") String query,@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
        query = query.trim();
        Page<ManagerDTO> managerpage = managerService.searchUser(query,PageRequest.of(page, 10));
        model.addAttribute("manager", managerpage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", managerpage.getTotalPages());
        return "manager-list";
    }

    @GetMapping("/admin/manager/{managerId}/detail")
    public String detailManager(@PathVariable("managerId") Long managerId, Model model) {
        ManagerDTO manager = managerService.getOneManager(managerId);
        model.addAttribute("manager", manager );
        return "manager-detail";
    }

    @PostMapping("/admin/manager/{managerId}/activate")
    public ResponseEntity<String> activeManager(@PathVariable("managerId") Long managerId) {
        UserEntity mng = userRepository.findById(managerId).get();
        if (!mng.getStatus().equals("active")){
            mng.setStatus("active");
            userRepository.save(mng);
            return ResponseEntity.ok("Activate Manager successfully");

        }else {
            logger.error("Failed to activate Manager");
            return ResponseEntity.badRequest().body("Manager already activated");
        }
    }

    @PostMapping("/admin/manager/{managerId}/deactivate")
    public ResponseEntity<String> unActiveManager(@PathVariable("managerId") Long managerId) {
        UserEntity mng = userRepository.findById(managerId).get();
        if (mng.getStatus().equals("active")){
            mng.setStatus("unactive");
            userRepository.save(mng);
            return ResponseEntity.ok("Deactivate Manager successfully");

        }else {
            logger.error("Failed to deactivate Manager");
            return ResponseEntity.badRequest().body("Manager already activated");
        }
    }
}
