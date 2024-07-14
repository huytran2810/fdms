package org.SWP391_FUHL_SE1825.FDMS.controller;

import org.SWP391_FUHL_SE1825.FDMS.dto.respone.PaymentRespone;
import org.SWP391_FUHL_SE1825.FDMS.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/payments")
public class PaymentController {
    private final IPaymentService iPaymentService;
    @Autowired
    public PaymentController(IPaymentService iPaymentService) {
        this.iPaymentService = iPaymentService;
    }


    @GetMapping("/history")
    public String list(@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
        Page<PaymentRespone> paymentList = iPaymentService.paymentList(page);
        model.addAttribute("paymentList", paymentList.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", paymentList.getTotalPages());
        return "admin_payments";
    }
    @GetMapping("/indi")
    public String indi(){
        return "payment_individual";
    }

}