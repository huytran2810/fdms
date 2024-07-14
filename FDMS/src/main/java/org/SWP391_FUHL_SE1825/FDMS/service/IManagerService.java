package org.SWP391_FUHL_SE1825.FDMS.service;

import org.SWP391_FUHL_SE1825.FDMS.dto.ManagerDTO;
import org.SWP391_FUHL_SE1825.FDMS.entity.ManagerSecondaryInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IManagerService {
    public Page<ManagerSecondaryInformation> listManager(Pageable pageable);
    public Page<ManagerDTO> searchUser(String query, Pageable pageable);

    Page<ManagerDTO> getAllManagers(Pageable pageable);

    ManagerDTO maptoDTO(ManagerSecondaryInformation manager);

    ManagerDTO getOneManager(Long managerId);
}
