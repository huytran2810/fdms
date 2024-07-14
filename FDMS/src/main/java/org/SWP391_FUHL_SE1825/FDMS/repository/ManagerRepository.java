package org.SWP391_FUHL_SE1825.FDMS.repository;
import org.SWP391_FUHL_SE1825.FDMS.dto.ManagerAJDTO;
import org.SWP391_FUHL_SE1825.FDMS.dto.StudentAJDTO;
import org.SWP391_FUHL_SE1825.FDMS.entity.ManagerSecondaryInformation;
import org.SWP391_FUHL_SE1825.FDMS.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<ManagerSecondaryInformation, Long> {
    @Query("SELECT u from ManagerSecondaryInformation u WHERE u.userEntity.fullName LIKE %?1% ")
    Page<ManagerSecondaryInformation> searchUser(String query, Pageable pageable);

    @Query("SELECT new org.SWP391_FUHL_SE1825.FDMS.dto.ManagerAJDTO(s.id,s.userEntity.fullName) FROM ManagerSecondaryInformation s WHERE s.userEntity.fullName LIKE %:query% ")
    List<ManagerAJDTO> searchManagerAJ(String query, Pageable pageable);
}
