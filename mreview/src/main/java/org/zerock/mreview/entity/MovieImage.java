package org.zerock.mreview.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "movie")
public class MovieImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum; // 이미지 번호

    private String uuid; // 고유 번호 생성

    private String imgName; // 이미지 이름

    private String path; // 이미지 경로

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

}
