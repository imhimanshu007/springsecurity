package com.learning.eazybankbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "notice_details")
public class Notice {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notice_generator"
    )

    @SequenceGenerator(
            name = "notice_generator",
            sequenceName = "notice_seq",
            allocationSize = 1
    )
    @Column(name = "notice_id")
    private Long noticeId;

    @Column(name = "notice_summary")
    private String noticeSummary;

    @Column(name = "notice_details")
    private String noticeDetails;

    @Column(name = "notice_beg_date")
    private LocalDateTime noticeBeginDate;

    @Column(name = "notice_end_date")
    private LocalDateTime noticeEndDate;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
}
