package org.SWP391_FUHL_SE1825.FDMS.service;

import org.SWP391_FUHL_SE1825.FDMS.entity.Bed;
import org.SWP391_FUHL_SE1825.FDMS.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface INewsService {
    public List<News> getAllNews();
    News saveNews(News news);
    News getNewsById(Long id);
    void delete(Long newsId);
    public List<News> getStudentNews();
    public List<News> getManagerNews();
    Page<News> list(Pageable pageable);
    Page<News> getStudentNewsPage(Pageable pageable);
    Page<News> getManagerNewsPage(Pageable pageable);
}
