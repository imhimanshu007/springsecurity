package com.learning.eazybankbackend.controller;

import com.learning.eazybankbackend.model.Notice;
import com.learning.eazybankbackend.repository.NoticeRepository;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
public class NoticesController {

    private final NoticeRepository noticeRepository;

    public NoticesController(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> getNotices(){
        Optional<List<Notice>> notices = noticeRepository.findAllActiveNotice();
        return notices.map(noticeList -> ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                .body(noticeList)).orElse(null);

    }
}
