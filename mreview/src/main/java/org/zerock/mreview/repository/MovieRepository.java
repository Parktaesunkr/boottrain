package org.zerock.mreview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.mreview.entity.Movie;

import java.util.List;


public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select m, avg(coalesce(r.grade,0)), count(distinct r) from Movie m" +
            " left outer join Review r on r.movie = m group by m")
    Page<Object[]>getListPage(Pageable pageable); // 페이지 처리


    @Query("select m, mi "+
            " from Movie m left outer join MovieImage mi on mi.movie = m "+
            " where m.mno =:mno") // @Query 사용시 @Param 사용 필요
    List<Object[]> getMovieWithAll(@Param("mno") Long mno); // 특정 영화 조회
}
