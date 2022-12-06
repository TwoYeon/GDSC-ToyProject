package com.gdsc.study.gdscspringbootstudy.domain.comments;

import com.gdsc.study.gdscspringbootstudy.domain.BaseTimeEntity;
import com.gdsc.study.gdscspringbootstudy.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Comments extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @Column(columnDefinition = "TEXT", length = 500, nullable = false)
    private String content_c;

    private String username;

    @Column(nullable = false)
    private Long groupNum;

    @Column(nullable = false)
    private int groupOrder;

    @Column(nullable = false)
    private int groupDepth;

    @Column(nullable = false)
    private String deleted;

    @Builder
    public Comments(Posts posts, String content_c, String username, Long groupNum, int groupOrder, int groupDepth, String deleted){
        this.posts = posts;
        this.content_c = content_c;
        this.username = username;
        this.groupNum = groupNum;
        this.groupOrder = groupOrder;
        this.groupDepth = groupDepth;
        this.deleted = deleted;
    }

}
