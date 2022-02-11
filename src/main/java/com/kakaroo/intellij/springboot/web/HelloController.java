package com.kakaroo.intellij.springboot.web;

import com.kakaroo.intellij.springboot.domain.posts.Posts;
import com.kakaroo.intellij.springboot.domain.posts.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    PostsRepository postsRepo;

    @GetMapping("/hello")
    public String get() {

        //save 메소드는 테이블 posts에 insert/update 쿼리를 실행한다.
        postsRepo.save(Posts.builder().content("KKK").title("title").author("author").build());

        List<Posts> list = postsRepo.findAll();
        System.out.println(list.get(0).getTitle());

        return "hello world";
    }
}
