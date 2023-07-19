package org.zerock.ex02.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex02.entity.Memo;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass(){
        System.out.println(memoRepository.getClass().getName());
    }

    @Test
    public  void  testInsertDummies(){
        IntStream.rangeClosed(1,100).forEach(i ->{
            Memo memo = Memo.builder().memoText("Sample..."+i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testSelect(){
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("=============================");
        if(result.isPresent()){
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    @Transactional
    @Test
    public  void  testSelect2(){
        // DB에 존재하는 데이터
        Long mno = 100L;
        Memo memo = memoRepository.getOne(mno);
        System.out.println("=============================");
        System.out.println(memo);
    }

    @Test
    public void testupdate(){
        Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();

        System.out.println(memoRepository.save(memo));
    }

    @Test
    public void testDelete(){
        Long mno =100L;
        memoRepository.deleteById(mno);
    }

    @Test
    public void TestPageDefault(){ // 페이징 테스트
        Pageable pageable = PageRequest.of(0,10);
        Page<Memo> result = memoRepository.findAll(pageable);
        System.out.println(result);
        System.out.println("--------------------------------------------------");
        System.out.println("Total Page: "+result.getTotalPages()); // 총 페이지 수
        System.out.println("Total Count: "+result.getTotalElements()); // 전체 갯수
        System.out.println("Page Number: "+result.getNumber()); // 현재 페이지 번호(0부터 시작)
        System.out.println("Page Size: "+result.getSize()); // 체이지당 데이터 갯수
        System.out.println("has next page?: "+result.hasNext()); // 다음페이지 존재 여부
        System.out.println("first page?: "+result.isFirst()); // 시작페이지 여부
    }

    @Test
    public void testSort(){ // 정렬 테스트
        Sort sort1 = Sort.by("mno").descending(); // 역순으로 정렬
        Pageable pageable = PageRequest.of(0,10,sort1);
        Page<Memo> result = memoRepository.findAll(pageable);
        result.get().forEach(memo->{
            System.out.println(memo);
        });
    }
}
