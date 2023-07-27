package org.zerock.board.dto;


import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO, EN> {

     // DTo 리스트
    private List<DTO> dtoList;
     // 총 페이지 번호
    private  int totalPage;
     // 현재 페이지 번호
    private int page;
     // 한 페이지 당 게시글 수
    private int size;
     // 시작 페이지 번호, 끝 페이지 버놓
    private int start, end;
     // 이전, 다음
    private boolean prev, next;
     // 페이지 번호 목록
    private List<Integer> pageList;

    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn){
        dtoList = result.stream().map(fn).collect(Collectors.toList());

        totalPage = result.getTotalPages();

        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable){
        this.page = pageable.getPageNumber()+1; // 0부터 시작하므로 1을 추가해 1부터 시작
        this.size = pageable.getPageSize();

         // temp end page
        int tempEnd = (int)(Math.ceil(page/10.0))*10; // 소수점에서 올림하여 페이지 목록 마지막 번호 출력

        start = tempEnd - 9;
        prev = start > 1;
        end = totalPage > tempEnd ? tempEnd : totalPage; // 전체 페이지 수 : 현재 페이지 목록 마지막 번호
        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }
}
