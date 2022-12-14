package com.gdsc.study.gdscspringbootstudy.web;

import com.gdsc.study.gdscspringbootstudy.domain.user.UserRepository;
import com.gdsc.study.gdscspringbootstudy.service.comments.CommentsService;
import com.gdsc.study.gdscspringbootstudy.web.dto.CommentsSaveRequestDto;
import com.gdsc.study.gdscspringbootstudy.web.dto.ReCommentsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("api/v1/comments/{postId}")
@RestController
public class CommentsApiController {

    private final CommentsService commentsService;
    private final UserRepository userRepository;

    @PostMapping("")  // 댓글 작성 api
    public Long save(@PathVariable Long postId, @RequestBody CommentsSaveRequestDto commentsSaveRequestDto){
//        System.out.println("http://localhost:8080/api/v1/comments/1 요청=================");
        return commentsService.save(postId, commentsSaveRequestDto);
    }

    @PostMapping("{commentId}/recomment")  // 대댓글 작성 api
    public Long save(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody ReCommentsSaveRequestDto reCommentsSaveRequestDto){
        return commentsService.saveRecomment(postId, commentId, reCommentsSaveRequestDto);
    }


    @PostMapping("{commentId}")  // 댓글 삭제 api =>
    public Long deleteComment(@PathVariable Long postId, @PathVariable Long commentId){
        return commentsService.deleteComment(commentId);
    }

    @DeleteMapping("{commentId}")  // 대댓글 삭제 api
    public Long delete(@PathVariable Long commentId) {
        commentsService.delete(commentId);
        return commentId;
    }

}
