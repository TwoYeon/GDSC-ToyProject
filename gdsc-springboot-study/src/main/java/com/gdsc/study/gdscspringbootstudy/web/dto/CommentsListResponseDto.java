package com.gdsc.study.gdscspringbootstudy.web.dto;

import com.gdsc.study.gdscspringbootstudy.domain.comments.Comments;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentsListResponseDto {

    private Long id;
    private String content_c;
    private String username;
    private LocalDateTime createdDate;

    public CommentsListResponseDto(Comments commentsEntity){
        this.id = commentsEntity.getId();
        this.content_c = commentsEntity.getContent_c();
        this.username = commentsEntity.getUsername();
        this.createdDate = commentsEntity.getCreatedDate();
    }
}
