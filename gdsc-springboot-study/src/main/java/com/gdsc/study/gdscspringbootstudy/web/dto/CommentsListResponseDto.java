package com.gdsc.study.gdscspringbootstudy.web.dto;

import com.gdsc.study.gdscspringbootstudy.domain.comments.Comments;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentsListResponseDto {

    private String content;
    private String username;
    private LocalDateTime createdDate;

    public CommentsListResponseDto(Comments commentsEntity){
        this.content = commentsEntity.getContent();
        this.username = commentsEntity.getUsername();
        this.createdDate = commentsEntity.getCreatedDate();
    }
}
