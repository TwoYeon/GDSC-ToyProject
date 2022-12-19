package com.gdsc.study.gdscspringbootstudy.web.dto;

import com.gdsc.study.gdscspringbootstudy.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;
    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(String imageUrl) {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .imgUrl(imageUrl)
                .build();
    }
}
