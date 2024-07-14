package org.SWP391_FUHL_SE1825.FDMS.service;

import org.SWP391_FUHL_SE1825.FDMS.dto.respone.RequestTypeRespone;
import org.SWP391_FUHL_SE1825.FDMS.entity.RequestApplicationType;


import java.util.List;

public interface IRequestTypeService {
    List<RequestTypeRespone> getRequestTypesbyManager();
    List<RequestTypeRespone> getRequestTypesbyStudent();
    RequestApplicationType getRequestType(long id);
}
