package com.gdsc.study.gdscspringbootstudy.web.dto;

import lombok.Getter;

@Getter
public class CommentsSaveRequestDto {  // 댓글 작성 dto

    private String content_c;
    private String username;
    private int groupOrder = 0;  // 대댓글의 순서
    private int groupDepth = 0;  // 깊이 (원댓글=0, 대댓글=1)
}
