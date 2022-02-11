package com.kakaroo.intellij.springboot.services.posts;

import com.kakaroo.intellij.springboot.domain.posts.Posts;
import com.kakaroo.intellij.springboot.domain.posts.PostsRepository;
import com.kakaroo.intellij.springboot.web.dto.PostsResponseDto;
import com.kakaroo.intellij.springboot.web.dto.PostsSaveRequestDto;
import com.kakaroo.intellij.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id not exist!! id="+id));

        //update시 repository에 query를 보내지 않는다. JPA의 엔티티를 영구저장하는 환경인 영속성 컨텍스트 때문이다.
        //해당 데이터의 값을 변경하면 트랜젝션이 끝나는 시점에 해당 테이블에 변경분을 반영한다. 이것을 더티체킹이라 한다.
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    // javax.transaction.Transactional 는 readOnly 등의 옵션을 허용하지 않는다.
    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id not exist!! id=" + id));
        return new PostsResponseDto(posts);
    }
    @Transactional(readOnly = true)
    public List<PostsResponseDto> findAll() {
        List<PostsResponseDto> list = postsRepository.findAll().stream().map(PostsResponseDto::new).collect(Collectors.toList());
        for(PostsResponseDto posts : list) {
            System.out.println(posts);
        }
        return list;
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id not exist!! id=" + id));
        postsRepository.delete(posts);
    }
}
