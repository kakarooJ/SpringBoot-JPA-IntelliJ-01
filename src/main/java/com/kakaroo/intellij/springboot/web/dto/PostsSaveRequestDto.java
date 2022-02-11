package com.kakaroo.intellij.springboot.web.dto;

import com.kakaroo.intellij.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Entity 클래스와 거의 유사한 형태임에도 Dto 클래스를 추가로 생성했다.
//절대로 테이블과 연결된 Entity 클래스를 Request/Response 클래스로 사용해서는 안 된다.
//Request/Response 용 DTO는 View를 위한 클래스라 자주 변경이 필요하기 때문이다.
//View Layer와 DB Layer의 역할 분리를 철저하게 하는게 좋다.
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder().title(title).content(content).author(author).build();
    }
}
