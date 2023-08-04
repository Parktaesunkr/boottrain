package org.zerock.mreview.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH) // LAZY 때문에 설정 됨
    // TEST에 @Transactional을 넣어도 에러 발생 가능성이 있으며 에러 발생시 위 코드를 넣어 LAZY로 느슨해진 로딩 시간을 동일하게 함
    List<Review> findByMovie(Movie movie);

    @Modifying
    @Query("delete from Review mr where mr.member =:member") // 위 두 에너테이션 시 한꺼번에 처리 가능
    void deleteByMember(@Param("member") Member member);
}
