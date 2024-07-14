package org.SWP391_FUHL_SE1825.FDMS.repository;

import org.SWP391_FUHL_SE1825.FDMS.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SemesterRepository extends JpaRepository<Semester, Long> {
    List<Semester> findAll();

    Semester findSemesterBySemesterName(String semesterName);
}
