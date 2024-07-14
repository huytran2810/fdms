package org.SWP391_FUHL_SE1825.FDMS.repository;

import org.SWP391_FUHL_SE1825.FDMS.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByRoleName(String Name);
}
