package com.example.noticeboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class Board {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    //댓글과 매핑을 해야함
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REVIEWLIST_ID")
    private ReviewList reviewList;

    private Integer reviewCount;

    public Board() {
    }

    public Board(String title, String content, ReviewList reviewList, Member member, Integer reviewCount) {
        this.title = title;
        this.content = content;
        this.reviewList = reviewList;
        this.member = member;
        this.reviewCount = reviewCount;
    }
}
