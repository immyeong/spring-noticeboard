package com.example.noticeboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "REVIEWS")
@Getter @Setter
public class ReviewList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer count;

    @OneToMany(mappedBy = "reviewList")
    private List<Review> reviews;

    public ReviewList(List<Review> reviews,Integer count) {
        this.reviews = reviews;
        this.count = count;
    }

    public ReviewList() {

    }
}
