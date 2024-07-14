package org.SWP391_FUHL_SE1825.FDMS.service.Impl;


import org.SWP391_FUHL_SE1825.FDMS.entity.Semester;
import org.SWP391_FUHL_SE1825.FDMS.repository.SemesterRepository;
import org.SWP391_FUHL_SE1825.FDMS.service.ISemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SemesterServiceImpl implements ISemesterService {
    @Autowired
    private SemesterRepository semesterRepository;
    @Override
    public Semester getSemester() {
        LocalDate date = LocalDate.now();
        List<Semester> list = semesterRepository.findAll();
        Optional<Semester> matchingSemester = list.stream()
                .filter(semester -> semester.isDateWithinRange(date))
                .findFirst();
        Semester semester;
        semester = matchingSemester.orElse(null);
        return semester;
    }
}
