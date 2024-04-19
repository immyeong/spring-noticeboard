package com.example.noticeboard.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ReviewListRepository {

    private static Map<Long, ReviewList> reviewListMap = new HashMap<>();
    private static long sequence = 0;

    public ReviewList save(ReviewList reviewList) {
        reviewList.setId(++sequence);
        reviewListMap.put(reviewList.getId(), reviewList);
        return reviewList;
    }
}
