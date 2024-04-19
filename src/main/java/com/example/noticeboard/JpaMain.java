package com.example.noticeboard;

import com.example.noticeboard.domain.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Component;

@Component
public class JpaMain {

    static MemberRepository memberRepository = new MemberRepository();

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("noticeboard");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{

            tx.commit();
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        }

        em.close();
        emf.close();
    }
}
