package org.SWP391_FUHL_SE1825.FDMS.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.SWP391_FUHL_SE1825.FDMS.entity.News;
import org.SWP391_FUHL_SE1825.FDMS.entity.UserEntity;
import org.SWP391_FUHL_SE1825.FDMS.repository.NewsRepository;
import org.SWP391_FUHL_SE1825.FDMS.repository.UserRepository;
import org.SWP391_FUHL_SE1825.FDMS.security.SecurityUtil;
import org.SWP391_FUHL_SE1825.FDMS.service.INewsService;
import org.SWP391_FUHL_SE1825.FDMS.service.Impl.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class NewsController {
    @Autowired
    private INewsService iNewsService;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsServiceImpl newsServiceImpl;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Cloudinary cloudinary;



    @GetMapping("/news")
    public String getAllNews(@RequestParam(value = "page", defaultValue = "0") int page,Model model) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUserName(username);
        Page<News> newsPage = null;
        PageRequest pageRequest = PageRequest.of(page, 4, Sort.by(Sort.Direction.DESC, "updatedAt"));

        List<News> allnews;
        if (user.getRole().getId() == 2) {
            newsPage = newsServiceImpl.getStudentNewsPage(pageRequest);
            model.addAttribute("allnews", newsPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", newsPage.getTotalPages());
        }else if (user.getRole().getId() == 3){
            newsPage = newsServiceImpl.getManagerNewsPage(pageRequest);
            model.addAttribute("allnews", newsPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", newsPage.getTotalPages());

        }else {
            allnews = newsServiceImpl.getAllNews();
            newsPage = newsServiceImpl.list(pageRequest);
            model.addAttribute("allnews", newsPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", newsPage.getTotalPages());
        }

        model.addAttribute("user", user );
        return "news-list";
    }


    @GetMapping("/news/{newsId}/detail")
    public String detail(@PathVariable("newsId") Long newsId, Model model) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUserName(username);
         News news = newsServiceImpl.getNewsById(newsId);
         model.addAttribute("news", news );
        model.addAttribute("user", user );
        return "news_detail";
    }


    @GetMapping("/admin/news/create")
    public String createNews(Model model) {
        News news = new News();
        model.addAttribute("news",news);
        return "news_create";
    }

    @PostMapping("/admin/news/create")
    public String handleFileUpload(@RequestParam("imageFile") MultipartFile file,
                                   RedirectAttributes redirectAttributes, @ModelAttribute("news") News news) throws IOException {
        if(!file.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

            String imageUrl = (String) uploadResult.get("url");

            news.setImage(imageUrl);
        }

        newsServiceImpl.saveNews(news);


        return "redirect:/news";
    }

    @PostMapping("/admin/news/{newsId}/delete")
    public ResponseEntity<String> deleteNews(@PathVariable("newsId") Long newsId) {
        newsServiceImpl.delete(newsId);
        return ResponseEntity.ok("Delete News successfully");
    }

    @GetMapping("/admin/news/{newsId}/update")
    public String updateNews(@PathVariable("newsId") Long newsId, Model model) {
        News news = newsServiceImpl.getNewsById(newsId);
        model.addAttribute("news", news );
        return "news_update";
    }

    @PostMapping("/admin/news/{newsId}/update")
    public String updateNews(@PathVariable("newsId") Long newsId,@RequestParam("content") String content,@RequestParam("imageFile") MultipartFile file,
                             RedirectAttributes redirectAttributes,@ModelAttribute("imageUrl") String image, @ModelAttribute("news") News news) throws IOException{
        news.setId(newsId);
        news.setUpdatedAt(Instant.now());
        content = content.replaceAll("<p>", "").replaceAll("</p>", "");
        news.setContent(content);

        if(!file.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

            String imageUrl = (String) uploadResult.get("url");

            news.setImage(imageUrl);
        }else {
            news.setImage(image);
        }

        newsServiceImpl.saveNews(news);
        return "redirect:/news";
    }
}

