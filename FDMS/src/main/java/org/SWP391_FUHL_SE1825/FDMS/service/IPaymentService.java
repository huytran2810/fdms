package org.SWP391_FUHL_SE1825.FDMS.service;


import org.SWP391_FUHL_SE1825.FDMS.dto.respone.PaymentRespone;
import org.SWP391_FUHL_SE1825.FDMS.entity.Payment;
import org.springframework.data.domain.Page;


public interface IPaymentService {
    Page<Payment> list(int index);
    Page<PaymentRespone> paymentList(int page);
}
