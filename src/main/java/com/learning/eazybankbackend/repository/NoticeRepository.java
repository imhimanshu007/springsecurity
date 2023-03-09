package com.learning.eazybankbackend.repository;

import com.learning.eazybankbackend.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query("SELECT n FROM Notice n WHERE CURDATE() BETWEEN n.noticeBeginDate AND n.noticeEndDate")
    Optional<List<Notice>> findAllActiveNotice();
}
