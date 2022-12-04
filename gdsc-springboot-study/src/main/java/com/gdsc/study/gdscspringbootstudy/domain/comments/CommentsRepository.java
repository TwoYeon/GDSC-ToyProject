package com.gdsc.study.gdscspringbootstudy.domain.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    @Query(value = "SELECT COUNT(*) FROM comments WHERE groupNum = :groupNum", nativeQuery = true)
    int mComments(Long groupNum);
}
