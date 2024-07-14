package org.SWP391_FUHL_SE1825.FDMS.controller;

import org.SWP391_FUHL_SE1825.FDMS.dto.request.ApiResponse;
import org.SWP391_FUHL_SE1825.FDMS.dto.request.RequestCreationRequest;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.RequestRespone;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.RequestTypeRespone;
import org.SWP391_FUHL_SE1825.FDMS.entity.RequestApplicationType;
import org.SWP391_FUHL_SE1825.FDMS.entity.UserEntity;
import org.SWP391_FUHL_SE1825.FDMS.security.SecurityUtil;
import org.SWP391_FUHL_SE1825.FDMS.service.IRequestService;
import org.SWP391_FUHL_SE1825.FDMS.service.IUserService;
import org.SWP391_FUHL_SE1825.FDMS.service.IRequestTypeService;
import org.SWP391_FUHL_SE1825.FDMS.utils.CommonUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/request")
public class RequestController {
    private final IRequestService iRequestService;
    private final IRequestTypeService IRequestTypeService;
    private final IUserService userService;

    public RequestController(IRequestService iRequestService, IRequestTypeService IRequestTypeService, IUserService userService) {
        this.iRequestService = iRequestService;
        this.IRequestTypeService = IRequestTypeService;
        this.userService = userService;
    }

    @GetMapping("")
    public String getAllRequest(@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
        Page<RequestRespone> request;
        request = iRequestService.getAllRequest(page);
        model.addAttribute("request", request.getContent());
        model.addAttribute("totalPages", request.getTotalPages());
        model.addAttribute("currentPage", page);
        return "requests";
    }

    @GetMapping("/detail")
    public String getAllRequestDetail() {
        return "request_details";
    }

    @GetMapping("/create")
    public String createView(Model model) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userService.getUser(username);
        List<RequestTypeRespone> request ;
        if(user.getRole().getId()==2){
            request=IRequestTypeService.getRequestTypesbyStudent();
        }else{
            request=IRequestTypeService.getRequestTypesbyManager();
        }
        model.addAttribute("request_type", request);
        model.addAttribute("userID",user.getId());
        return "request_create";
    }
    @PostMapping("/create")
    public String createRequest(@RequestBody RequestCreationRequest request){

        return "";
    }
}
