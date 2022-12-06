package com.gdsc.study.gdscspringbootstudy.service.comments;

import com.gdsc.study.gdscspringbootstudy.domain.comments.Comments;
import com.gdsc.study.gdscspringbootstudy.domain.comments.CommentsRepository;
import com.gdsc.study.gdscspringbootstudy.domain.posts.PostsRepository;
import com.gdsc.study.gdscspringbootstudy.web.dto.CommentsSaveRequestDto;
import com.gdsc.study.gdscspringbootstudy.web.dto.ReCommentsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(Long postId, CommentsSaveRequestDto commentsSaveRequestDto){
        Comments commentEntity = Comments.builder()
                .posts(postsRepository.findById(postId).get())
                .content_c(commentsSaveRequestDto.getContent_c())
                .username(commentsSaveRequestDto.getUsername())
                .groupOrder(commentsSaveRequestDto.getGroupOrder())  // 댓글이면 0
                .groupNum(0L)
                .groupDepth(commentsSaveRequestDto.getGroupDepth())
                .deleted("X")
                .build();

        commentsRepository.save(commentEntity);
        commentEntity.setGroupNum(commentEntity.getId());
        return commentsRepository.save(commentEntity).getId();
    }

    @Transactional
    public Long saveRecomment(Long postId, Long commentId, ReCommentsSaveRequestDto reCommentsSaveRequestDto){
        Optional<Comments> originComment = commentsRepository.findById(commentId);  // 원댓글 가져오기
        Long num = originComment.get().getId();
        commentsRepository.mComments(num);

        Comments commentEntity = Comments.builder()
                .posts(postsRepository.findById(postId).get())
                .content_c(reCommentsSaveRequestDto.getContent_c())
                .username(reCommentsSaveRequestDto.getUsername())
                .groupOrder(commentsRepository.mComments(num))  // 대댓글 순서, 1부터 시작
                .groupNum(num)
                .groupDepth(reCommentsSaveRequestDto.getGroupDepth())
                .deleted("X")
                .build();

        return commentsRepository.save(commentEntity).getId();
    }


}
