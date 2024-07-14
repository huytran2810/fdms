package org.SWP391_FUHL_SE1825.FDMS.service;

import org.SWP391_FUHL_SE1825.FDMS.dto.request.RequestCreationRequest;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.RequestRespone;
import org.SWP391_FUHL_SE1825.FDMS.entity.RequestApplication;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRequestService {

    void requestBooking(RequestApplication requestApplication);
    Page<RequestRespone> getAllRequest(int page);
    void createRequest(RequestCreationRequest request);
}
