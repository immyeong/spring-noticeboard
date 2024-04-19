package com.example.noticeboard.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberRepository {

   private static final Map<Long, Member> members = new HashMap<>();
   private static long sequence = 0L;

    public MemberRepository() {
    }

    public Member save(Member member){
       member.setId(++sequence);
       members.put(member.getId(), member);
       return member;
   }

   public void update(Long id, Member updateParam){
       Member findMember = findId(id);
       findMember.setUsername(updateParam.getUsername());
   }

   public Member findId(Long id){
       return members.get(id);
   }

   public List<Member> findAll(){
        return new ArrayList<>(members.values());
   }

    /**
     * 테스트용
     */

    public void clear(){
        members.clear();
   }

}
