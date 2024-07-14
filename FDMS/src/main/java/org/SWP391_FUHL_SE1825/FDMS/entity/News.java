package org.SWP391_FUHL_SE1825.FDMS.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@Entity
@Table(name = "news")
public class News extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id", nullable = false)
    private Long id;


    @Size(max = 50)
    @Column(name = "category", length = 50)
    private String category;

    @Lob
    @Column(name = "content")
    private String content;

    @Lob
    @Column(name = "image")
    private String image;

    @Size(max = 255)
    @Column(name = "title")
    private String title;

}