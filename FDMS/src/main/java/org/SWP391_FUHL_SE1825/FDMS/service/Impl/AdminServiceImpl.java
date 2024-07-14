package org.SWP391_FUHL_SE1825.FDMS.service.Impl;

import org.SWP391_FUHL_SE1825.FDMS.entity.AdminSecondaryInformation;
import org.SWP391_FUHL_SE1825.FDMS.entity.UserEntity;
import org.SWP391_FUHL_SE1825.FDMS.repository.AdminRepository;
import org.SWP391_FUHL_SE1825.FDMS.service.IAdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements IAdminService {
    private AdminRepository adminRepository;
    @Override
    public AdminSecondaryInformation getAdminbyUserId(UserEntity user) {
        return null;
       // return adminRepository.findById(user.getAdminSecondaryInformations().iterator().next().getAdmin_id()).get();
    }
}
