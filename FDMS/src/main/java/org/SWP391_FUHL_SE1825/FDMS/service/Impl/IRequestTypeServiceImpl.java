package org.SWP391_FUHL_SE1825.FDMS.service.Impl;

import lombok.AllArgsConstructor;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.RequestTypeRespone;
import org.SWP391_FUHL_SE1825.FDMS.entity.RequestApplicationType;
import org.SWP391_FUHL_SE1825.FDMS.repository.RequestTypeRepository;
import org.SWP391_FUHL_SE1825.FDMS.service.IRequestTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IRequestTypeServiceImpl implements IRequestTypeService {
    final
    RequestTypeRepository requestTypeRepository;


    @Override
    public List<RequestTypeRespone> getRequestTypesbyManager() {
        return requestTypeRepository.findByManager();
    }
    @Override
    public List<RequestTypeRespone> getRequestTypesbyStudent() {
        return requestTypeRepository.findByStudent();
    }

    @Override
    public RequestApplicationType getRequestType(long id) {
        return requestTypeRepository.findById(id);
    }
}
