package com.gdsc.study.gdscspringbootstudy.web;

import com.gdsc.study.gdscspringbootstudy.domain.user.UserRepository;
import com.gdsc.study.gdscspringbootstudy.service.comments.CommentsService;
import com.gdsc.study.gdscspringbootstudy.web.dto.CommentsSaveRequestDto;
import com.gdsc.study.gdscspringbootstudy.web.dto.ReCommentsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("api/v1/comments")
@RestController
public class CommentsApiController {

    private final CommentsService commentsService;
    private final UserRepository userRepository;

    @PostMapping("/{postId}")  // 댓글 작성 api
    public Long save(@PathVariable Long postId, @RequestBody CommentsSaveRequestDto commentsSaveRequestDto){
        System.out.println("http://localhost:8080/api/v1/comments/1 요청=================");
        return commentsService.save(postId, commentsSaveRequestDto);
    }

    @PostMapping("/{postId}/{commentId}/recomment")  // 대댓글 작성 api
    public Long save(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody ReCommentsSaveRequestDto reCommentsSaveRequestDto){
        return commentsService.saveRecomment(postId, commentId, reCommentsSaveRequestDto);
    }

//    private User getPrincipal(){  // 로그인한 현재 사용자
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("=====getPrincipal=====");
//        System.out.println(authentication.getName());
//        return userRepository.findByName(authentication.getName()).get();
//    }

}
