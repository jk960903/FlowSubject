package com.example.flowsubject.DAO.CustomExtension;

import com.example.flowsubject.VO.CustomExtensionVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//커스텀 확장자 레포지토리
@Repository
public interface CustomExtensionRepository extends JpaRepository<CustomExtensionVO,Long> {
    //Idx를 통한 커스텀텀확장자 검색
    public List<CustomExtensionVO> findByIdx(Long idx);

    //extension_name을 통한 커스텀 확장자 검색
    public List<CustomExtensionVO> findCustomExtensionVOByExtensionname(String extensionname);

}
