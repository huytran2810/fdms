package org.SWP391_FUHL_SE1825.FDMS.repository;
import org.SWP391_FUHL_SE1825.FDMS.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends  JpaRepository<News, Long> {
    @Query("SELECT n from News n WHERE n.category LIKE 'All' or n.category LIKE 'Student' ")
    List<News> StudentNews();

    @Query("SELECT n from News n WHERE n.category LIKE 'All' or n.category LIKE 'Manager' ")
    List<News> ManagerNews();

    @Query("SELECT n from News n WHERE n.category LIKE 'All' or n.category LIKE 'Student' ")
    Page<News> StudentNewsPage(Pageable pageable);

    @Query("SELECT n from News n WHERE n.category LIKE 'All' or n.category LIKE 'Manager' ")
    Page<News> ManagerNewsPage(Pageable pageable);
}
