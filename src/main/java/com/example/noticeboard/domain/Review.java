package com.example.noticeboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Review {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REVIEWS_ID")
    private ReviewList reviewList;

    public Review() {
    }

    public Review(Member member, String content) {
        this.member = member;
        this.content = content;
    }
}
