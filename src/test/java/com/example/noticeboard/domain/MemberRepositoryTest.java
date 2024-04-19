package com.example.noticeboard.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@RunWith(SpringRunner.class)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    void afterEach(){
        memberRepository.clear();
    }

    @Test
    void save(){
        //given
        Member member = new Member("홍길동");

        //when
        Member saveMember = memberRepository.save(member);

        //then
        assertThat(saveMember.getId()).isEqualTo(member.getId());
    }

    @Test
    void findAllMember(){
        //given
        Member member1= new Member("홍길동");
        Member member2= new Member("홍이동");

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> members = memberRepository.findAll();

        //then
        assertThat(members.size()).isEqualTo(2);
        assertThat(members).contains(member1,member2);
    }

    @Test
    void updateMember(){
        //given
        Member member1= new Member("홍길동");
        memberRepository.save(member1);
        Long member1Id = member1.getId();

        //when
        Member updateParam = new Member("홍이동");
        memberRepository.update(member1Id, updateParam);
        Member findMember = memberRepository.findId(member1Id);

        //then
        assertThat(findMember.getUsername()).isEqualTo(updateParam.getUsername());

    }
}