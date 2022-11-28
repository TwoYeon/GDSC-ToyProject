package com.gdsc.study.gdscspringbootstudy.domain.comments;

import com.gdsc.study.gdscspringbootstudy.domain.posts.Posts;
import com.gdsc.study.gdscspringbootstudy.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @Column(columnDefinition = "TEXT", length = 500, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Long groupNum;

    @Column(nullable = false)
    private int groupOrder;

    @Column(nullable = false)
    private int groupDepth;

    @Column(nullable = false)
    private String deleted;

    @CreatedDate
    private LocalDateTime createdDate;

    @Builder
    public Comments(String content, Long groupNum, int groupOrder, int groupDepth, String deleted){
        this.content = content;
        this.groupNum = groupNum;
        this.groupOrder = groupOrder;
        this.groupDepth = groupDepth;
        this.deleted = deleted;
    }

}
