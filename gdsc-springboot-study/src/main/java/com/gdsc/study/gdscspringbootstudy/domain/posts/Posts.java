package com.gdsc.study.gdscspringbootstudy.domain.posts;

import com.gdsc.study.gdscspringbootstudy.domain.BaseTimeEntity;
import com.gdsc.study.gdscspringbootstudy.domain.comments.Comments;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Comments> comments;

    private String imgName;

    private String imgUrl;

    @Builder
    public Posts(String title, String content, String author, String imgName, String imgUrl) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
