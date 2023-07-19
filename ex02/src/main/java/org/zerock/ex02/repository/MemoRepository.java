package org.zerock.ex02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.ex02.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> {

}
