package org.SWP391_FUHL_SE1825.FDMS.service.Impl;

import lombok.AllArgsConstructor;
import org.SWP391_FUHL_SE1825.FDMS.dto.respone.AvailableBedResponse;
import org.SWP391_FUHL_SE1825.FDMS.entity.Bed;
import org.SWP391_FUHL_SE1825.FDMS.repository.BedRepository;
import org.SWP391_FUHL_SE1825.FDMS.service.IBedService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class BedServiceImpl implements IBedService {
    private final BedRepository bedRepository;

    @Override
    public Page<Bed> list(int index, String status, Integer  roomId) {
        Pageable paging = PageRequest.of(index, 10);
        Page<Bed> pagedResult = bedRepository.findAllByStatus(paging, status == null ? "-1" : status, roomId == null ? 0 : roomId);

        return pagedResult;
    }

    @Override
    public Page<AvailableBedResponse> availableList(int index, String status, Integer roomId) {

        Pageable paging = PageRequest.of(index, 10);
        Page<AvailableBedResponse> bedsWithNoStudent = bedRepository.findAvailableBeds(paging, status == null ? "-1" : status, roomId == null ? 0 : roomId);

        return bedsWithNoStudent;
    }
}
