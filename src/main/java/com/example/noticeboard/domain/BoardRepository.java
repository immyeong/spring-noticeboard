package com.example.noticeboard.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardRepository {

    private static final Map<Long, Board> boards = new HashMap<>();
    private static long sequence = 0L;

    public Board save(Board board){
        board.setId(++sequence);
        boards.put(board.getId(), board);

        return board;
    }

    public Board findById(Long id){
        return boards.get(id);
    }

    public List<Board> findAll(){
        return new ArrayList<>(boards.values());
    }

    public void update(Long id, Board updateParam){
        Board findBoard = findById(id);
        findBoard.setContent(updateParam.getContent());
        findBoard.setTitle(updateParam.getTitle());
        findBoard.setMember(updateParam.getMember());
        findBoard.setReviewCount(updateParam.getReviewCount());
        findBoard.setReviewList(updateParam.getReviewList());
    }

    public void delete(Long id){
        boards.remove(id);
    }

    /**
     * 테스트용
     */
    public void clear(){
        boards.clear();
    }
}
