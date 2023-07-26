package org.zerock.guestbook.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.guestbook.dto.GuestbookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.Guestbook;

@SpringBootTest
public class GuestbookServiceTests {

    @Autowired
   private GuestbookService service;

    @Test
   public void testRegister(){

       GuestbookDTO guestbookDTO = GuestbookDTO.builder()
               .title("Sample Title...")
               .content("Sample Content...")
               .writer("user0")
               .build();

       System.out.println(service.register(guestbookDTO));
   }

   @Test
   public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

       System.out.println("PREV: "+resultDTO.isPrev());
       System.out.println("NEXT: "+resultDTO.isNext());
       System.out.println("TOTAL: "+resultDTO.getTotalPage());
       System.out.println("------------------------------------------");
       for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()){
           System.out.println(guestbookDTO);
       }
       System.out.println("================================================="); // 화면에 출력될 페이지 번호
       resultDTO.getPageList().forEach(i-> System.out.println(i));
   }

   @Test
   public void testSearch(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("tc")
                .keyword("한글")
                .build();

        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

       System.out.println("PREV: "+resultDTO.isPrev()); // 테스트 경과 이전 버튼 유무
       System.out.println("NEXT: "+resultDTO.isNext()); // 테스트 결과 다음 버튼 유무
       System.out.println("TOTAL: "+resultDTO.getTotalPage()); // 테스트 결과 총 수
       System.out.println("------------------------------------------");
       for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()){
           System.out.println(guestbookDTO);
       }
       System.out.println("================================================="); // 화면에 출력될 페이지 번호
       resultDTO.getPageList().forEach(i-> System.out.println(i));
   }
}
