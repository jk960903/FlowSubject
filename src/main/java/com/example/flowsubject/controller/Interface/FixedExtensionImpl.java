package com.example.flowsubject.controller.Interface;

import com.example.flowsubject.APIResult.APIResult;
import com.example.flowsubject.VO.FixedExtensionVO;
import com.example.flowsubject.VO.ReqeustVO.FixedExtension.CreateFixedExtensionRequestVO;
import com.example.flowsubject.VO.ReqeustVO.FixedExtension.DeleteFixedExtensionRequestVO;
import com.example.flowsubject.VO.ReqeustVO.FixedExtension.UpdateFixedExtensionRequestVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//고정확장자 인터페이스
public interface FixedExtensionImpl {

    //고정확장자 데이터 Read
    public APIResult<List<FixedExtensionVO>> GetFixedExtension(HttpServletRequest request);

    //고정확장자 데이터 Update
    public APIResult<FixedExtensionVO> UpdateFixedExtension(UpdateFixedExtensionRequestVO updateFixedExtensionRequestVO , HttpServletRequest request);

    //고정확장자 데이터 Create(추가확장 개발) 현재 직접 사용X
    public APIResult<FixedExtensionVO> CreateFixedExtension(CreateFixedExtensionRequestVO createFixedExtensionRequestVO, HttpServletRequest request);

    //고정확장자 데이터 Delete(추가 확장 개발) 현재 직접 사용 X
    public APIResult<Integer> DeleteFixedExtension(DeleteFixedExtensionRequestVO deleteFixedExtensionRequestVO, HttpServletRequest request);
}
