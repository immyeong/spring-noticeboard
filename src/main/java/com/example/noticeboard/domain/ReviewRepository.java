package com.example.noticeboard.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReviewRepository {
    private static final Map<Long, Review> reviews = new HashMap<>();
    private static long sequence = 0L;
    private static Integer reviewCount = 0;

    //리뷰 등록, 삭제, 수정, 단건조회,모두 조회,갯수 카운트
    public Review save(Review review){
        review.setId(++sequence);
        Review saveReview = reviews.put(review.getId(), review);
        reviewCount++;
        return saveReview;
    }

    public Review findById(Long id){
        return reviews.get(id);
    }

    public List<Review> findAll(){
        return new ArrayList<>(reviews.values());
    }

    public void update(Long id, Review updateParam){
        Review findReview = findById(id);
        findReview.setContent(updateParam.getContent());
        findReview.setMember(updateParam.getMember());
    }

    public void delete(Long id){
        reviews.remove(id);
    }

    public Integer reviewCount(){
        return reviewCount;
    }
}
