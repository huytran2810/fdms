package org.SWP391_FUHL_SE1825.FDMS.service.Impl;

import lombok.AllArgsConstructor;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.PaymentRespone;
import org.SWP391_FUHL_SE1825.FDMS.entity.Payment;
import org.SWP391_FUHL_SE1825.FDMS.repository.PaymentRepository;
import org.SWP391_FUHL_SE1825.FDMS.service.IPaymentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements IPaymentService {
    PaymentRepository paymentRepository ;

    @Override
    public Page<Payment> list(int index) {
        Pageable paging = PageRequest.of(index, 10);
        return null;
    }

    @Override
    public Page<PaymentRespone> paymentList(int page) {
        Pageable paging = PageRequest.of(page, 10);
        Page<PaymentRespone> paymentRespones = paymentRepository.findAllPaymentResponse(paging);
        return paymentRespones;
    }
}
