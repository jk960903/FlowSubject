package com.example.flowsubject.DAO.FixedExtension;

import com.example.flowsubject.VO.FixedExtensionVO;
import com.example.flowsubject.VO.ReqeustVO.FixedExtension.CreateFixedExtensionRequestVO;
import com.example.flowsubject.VO.ReqeustVO.FixedExtension.DeleteFixedExtensionRequestVO;
import com.example.flowsubject.VO.ReqeustVO.FixedExtension.UpdateFixedExtensionRequestVO;

import java.util.List;

public interface FixedExtensionServiceImpl {

    //고정 확장자 받아오기
    public List<FixedExtensionVO> GetFixedExtensions() throws Exception;

    //고정 확장자 체크 상태 업데이트
    public FixedExtensionVO UpdateFixedExtension(UpdateFixedExtensionRequestVO updateFixedExtensionRequestVO) throws Exception;

    //고정 확장자 생성
    public FixedExtensionVO CreateFixedExtension(CreateFixedExtensionRequestVO createFixedExtensionRequestVO) throws Exception;


    //고정 확장자 삭제
    public Integer DeleteFixedExtension(DeleteFixedExtensionRequestVO deleteFixedExtensionRequestVO) throws Exception;
}
