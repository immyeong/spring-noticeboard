package com.example.noticeboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String username;

    @OneToMany(mappedBy = "member")
    private List<Board> boardList;

    public Member(String username) {
        this.username = username;
    }

    public Member() {

    }

}
