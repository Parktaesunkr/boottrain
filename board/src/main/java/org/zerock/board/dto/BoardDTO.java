package org.zerock.board.dto;


import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long bno;

    private String title; // 게시글 제목

    private String content; // 게시글 내용

    private String writerEmail; // 작성자 이메일 (id)

    private String writerName; // 작성자 이름

    private LocalDateTime regDate; // 작성 일자 데이터

    private LocalDateTime modDate; // 수정 일자 데이터

    private int replyCount; // 해당 게시글의 댓글 수
}
