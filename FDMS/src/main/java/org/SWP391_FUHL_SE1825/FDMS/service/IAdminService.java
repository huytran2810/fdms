package org.SWP391_FUHL_SE1825.FDMS.service;

import org.SWP391_FUHL_SE1825.FDMS.entity.AdminSecondaryInformation;
import org.SWP391_FUHL_SE1825.FDMS.entity.UserEntity;


public interface IAdminService {
    public AdminSecondaryInformation getAdminbyUserId(UserEntity user);
}
