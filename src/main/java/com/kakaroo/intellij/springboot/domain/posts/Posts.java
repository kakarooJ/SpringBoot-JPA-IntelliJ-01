package com.kakaroo.intellij.springboot.domain.posts;

import com.fasterxml.classmate.GenericType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
//Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다.
//대신, 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼수 있는 메소드를 추가해야 한다.
//생성자 또는 Builder를 통해 최종값을 채운 후, DB에 삽입한다.
//값 변경이 필요한 경우 해당 이벤트에 맞는 public 메소드를 호출하여 변경한다.
@NoArgsConstructor
@Entity //클래스의 카멜케이스이름을 언더스코어 네이밍으로 테이블이름을 만든다.
//ex)SalesManager.java -> sales_manager table
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)    //기본값이 VARCHAR(255)
    private String content;

    private String author;

    @Builder    //Builder pattern class를 생성 ; 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함됨
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
