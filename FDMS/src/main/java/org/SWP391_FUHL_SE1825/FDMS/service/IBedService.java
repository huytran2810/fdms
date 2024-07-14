package org.SWP391_FUHL_SE1825.FDMS.service;

import org.SWP391_FUHL_SE1825.FDMS.dto.respone.AvailableBedResponse;
import org.SWP391_FUHL_SE1825.FDMS.entity.Bed;
import org.springframework.data.domain.Page;


public interface IBedService {
    Page<Bed> list(int index, String status, Integer  roomId);
    Page<AvailableBedResponse> availableList(int index, String status, Integer roomId);
}
