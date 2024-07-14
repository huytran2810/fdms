package org.SWP391_FUHL_SE1825.FDMS.service.Impl;

import org.SWP391_FUHL_SE1825.FDMS.entity.News;
import org.SWP391_FUHL_SE1825.FDMS.entity.UserEntity;
import org.SWP391_FUHL_SE1825.FDMS.repository.NewsRepository;
import org.SWP391_FUHL_SE1825.FDMS.repository.UserRepository;
import org.SWP391_FUHL_SE1825.FDMS.security.SecurityUtil;
import org.SWP391_FUHL_SE1825.FDMS.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements INewsService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public News saveNews(News news) {
        return newsRepository.save(news);
    }

    @Override
    public News getNewsById(Long id) {
        return newsRepository.findById(id).get();
    }

    @Override
    public void delete(Long newsId) {
        newsRepository.deleteById(newsId);
    }

    @Override
    public List<News> getStudentNews() {
        List<News> news = newsRepository.StudentNews();
        return news;
    }

    @Override
    public List<News> getManagerNews() {
        List<News> news = newsRepository.ManagerNews();
        return news;
    }


    public Page<News> list(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    @Override
    public Page<News> getStudentNewsPage(Pageable pageable) {
        return newsRepository.StudentNewsPage(pageable);
    }

    @Override
    public Page<News> getManagerNewsPage(Pageable pageable) {
        return newsRepository.ManagerNewsPage(pageable);
    }


}
