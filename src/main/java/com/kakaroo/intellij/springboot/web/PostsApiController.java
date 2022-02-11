package com.kakaroo.intellij.springboot.web;

import com.kakaroo.intellij.springboot.services.posts.PostsService;
import com.kakaroo.intellij.springboot.web.dto.PostsResponseDto;
import com.kakaroo.intellij.springboot.web.dto.PostsSaveRequestDto;
import com.kakaroo.intellij.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable("id") Long id) {
        return postsService.findById(id);
    }

    @GetMapping("/api/v1/posts/")
    public List<PostsResponseDto> findAll() {
        return postsService.findAll();
    }

    @PostMapping("/api/v1/posts/")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable("id") Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
