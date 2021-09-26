package com.example.flowsubject.DAO.FixedExtension;

import com.example.flowsubject.VO.FixedExtensionVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//고정 확장자 레포지토리
@Repository
public interface FixedExtensionRepository extends JpaRepository<FixedExtensionVO, Long> {

    //idx를 통한 검색
    public List<FixedExtensionVO> findByIdx(Long idx);

}
