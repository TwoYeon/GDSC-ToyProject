package com.gdsc.study.gdscspringbootstudy.service.posts;

import com.gdsc.study.gdscspringbootstudy.domain.posts.Posts;
import com.gdsc.study.gdscspringbootstudy.domain.posts.PostsRepository;
import com.gdsc.study.gdscspringbootstudy.web.dto.PostsListResponseDto;
import com.gdsc.study.gdscspringbootstudy.web.dto.PostsResponseDto;
import com.gdsc.study.gdscspringbootstudy.web.dto.PostsSaveRequestDto;
import com.gdsc.study.gdscspringbootstudy.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

//    @Value("${logging.file.path}")
    private String uploadFolder = "C:/workspace/springbootwork/upload/";

    @Transactional
    public Long save(MultipartFile multipartFile, String title, String content, String author) {
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + multipartFile.getOriginalFilename();

        System.out.println("이미지 파일 이름: " + imageFileName);

        Path imageFilePath = Paths.get(uploadFolder + imageFileName);

        try{
            Files.write(imageFilePath, multipartFile.getBytes());
        } catch (Exception e){
            e.printStackTrace();
        }

        Posts posts = Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .imgUrl(imageFileName)
                .build();
        postsRepository.save(posts);

        return posts.getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);
    }
}
