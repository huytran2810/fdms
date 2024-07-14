package org.SWP391_FUHL_SE1825.FDMS.service.Impl;

import org.SWP391_FUHL_SE1825.FDMS.dto.request.RequestCreationRequest;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.RequestRespone;
import org.SWP391_FUHL_SE1825.FDMS.entity.RequestApplication;
import org.SWP391_FUHL_SE1825.FDMS.repository.RequestRepository;
import org.SWP391_FUHL_SE1825.FDMS.service.IRequestService;
import org.SWP391_FUHL_SE1825.FDMS.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements IRequestService {
    @Autowired
    private RequestRepository requestRepository;



    @Override
    public void requestBooking(RequestApplication requestApplication) {
        requestRepository.save(requestApplication);
    }

    @Override
    public Page<RequestRespone> getAllRequest(int page) {
        Pageable paging = PageRequest.of(page, 10);
        Page<RequestRespone> list = requestRepository.findRequestApplications(paging);
        return list;
    }

    @Override
    public void createRequest(RequestCreationRequest request) {
        Long id = CommonUtils.generateUniqueId();
        RequestApplication.builder().id(id).requestContent(request.getContent()).status("pending").requestApplicationTypeId(request.getType()).build();
    }
}
