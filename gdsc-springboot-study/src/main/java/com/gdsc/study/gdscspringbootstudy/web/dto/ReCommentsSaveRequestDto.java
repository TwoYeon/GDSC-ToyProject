package com.gdsc.study.gdscspringbootstudy.web.dto;

import lombok.Getter;

@Getter
public class ReCommentsSaveRequestDto {

    private String content_c;
    private String username;
    private int groupDepth = 1;  // 깊이 (원댓글=0, 대댓글=1)
}
