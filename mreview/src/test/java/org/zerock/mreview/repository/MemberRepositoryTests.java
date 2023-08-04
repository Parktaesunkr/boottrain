package org.zerock.mreview.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.zerock.mreview.entity.Member;

import javax.transaction.Transactional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMembers(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Member member = Member.builder()
                    .email("r"+i+"@zerock.org")
                    .pw("1111")
                    .nickname("reviewer"+i)
                    .build();
            memberRepository.save(member);
        });
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteMember(){
        Long mid = 2L;

        Member member = Member.builder()
                .mid(mid)
                .build();
        /*memberRepository.deleteById(mid); // mid 1번의 리뷰가 존재 시 에러 발생
        reviewRepository.deleteByMember(member);*/

        reviewRepository.deleteByMember(member); // 순서 상의 문제로 인한 에러 였음
        memberRepository.deleteById(mid); // 한번에 삭제 하는 것이 아닌 하나 씩 삭제함 -> 반복
    }

}
