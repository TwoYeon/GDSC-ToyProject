package com.gdsc.study.gdscspringbootstudy.web;

import com.gdsc.study.gdscspringbootstudy.config.auth.LoginUser;
import com.gdsc.study.gdscspringbootstudy.config.auth.dto.SessionUser;
import com.gdsc.study.gdscspringbootstudy.service.comments.CommentsService;
import com.gdsc.study.gdscspringbootstudy.service.posts.PostsService;
import com.gdsc.study.gdscspringbootstudy.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final CommentsService commentsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }

    @GetMapping("/posts/detail/{id}")
    public String postsDetail(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("detail", dto);
        model.addAttribute("comments", commentsService.findAllDesc(id));
        return "posts-detail";
    }
}
