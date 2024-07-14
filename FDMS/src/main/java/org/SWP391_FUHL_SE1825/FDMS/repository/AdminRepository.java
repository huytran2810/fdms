package org.SWP391_FUHL_SE1825.FDMS.repository;

import org.SWP391_FUHL_SE1825.FDMS.entity.AdminSecondaryInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminSecondaryInformation, Long> {
    //Optional<AdminSecondaryInformation> findByUser(String username);
}
