package org.SWP391_FUHL_SE1825.FDMS.service.Impl;

import org.SWP391_FUHL_SE1825.FDMS.dto.ManagerDTO;
import org.SWP391_FUHL_SE1825.FDMS.entity.ManagerSecondaryInformation;
import org.SWP391_FUHL_SE1825.FDMS.repository.ManagerRepository;
import org.SWP391_FUHL_SE1825.FDMS.service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements IManagerService {
    @Autowired
    ManagerRepository managerRepository;
    @Override
    public Page<ManagerSecondaryInformation> listManager(Pageable pageable) {
        return managerRepository.findAll(pageable);
    }
    @Override
    public Page<ManagerDTO> searchUser(String query, Pageable pageable) {
        return managerRepository.searchUser(query,pageable).map(this::maptoDTO);
    }

    @Override
    public Page<ManagerDTO> getAllManagers(Pageable pageable) {
        return managerRepository.findAll(pageable)
                .map(this::maptoDTO);
    }

    @Override
    public ManagerDTO getOneManager(Long managerId){
        return managerRepository.findById(managerId).map(this::maptoDTO).get();
    }
    @Override
    public ManagerDTO maptoDTO(ManagerSecondaryInformation manager) {
        ManagerDTO dto = new ManagerDTO();
        dto.setId(manager.getId());
        dto.setAvatarImage(manager.getUserEntity().getAvatarImage());
        dto.setUserId(manager.getUserEntity().getId());
        dto.setEmail(manager.getUserEntity().getEmail());
        dto.setDob(manager.getUserEntity().getDateOfBirth());
        dto.setFullName(manager.getUserEntity().getFullName());
        dto.setGender(manager.getUserEntity().getGender());
        dto.setPhone(manager.getUserEntity().getPhone());
        dto.setAddress(manager.getUserEntity().getAddress());
        dto.setStatus(manager.getUserEntity().getStatus());
        dto.setBuildingName(manager.getBuilding().getBuildingname());
        return dto;
    }
}
