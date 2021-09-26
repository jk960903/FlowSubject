package com.example.flowsubject.DAO.CustomExtension;

import com.example.flowsubject.VO.CustomExtensionVO;
import com.example.flowsubject.VO.ReqeustVO.CustomExtension.CreateCustomExtensionRequest;
import com.example.flowsubject.VO.ReqeustVO.CustomExtension.DeleteCustomExtensionRequest;
import com.example.flowsubject.VO.ReqeustVO.CustomExtension.UpdateCustomExtensionRequest;

import java.util.List;

//커스텀 확장자 서비스 impl
public interface CustomExtensionServiceImpl {
    //커스텀 확장자 전체 가져오기
    public List<CustomExtensionVO> GetCustomExtensionList();

    //커스텀확장자 중복 체크
    public boolean CheckCustomExtensionDuplicate(String extension_name) throws Exception;

    //커스텀 확장자 생성
    public CustomExtensionVO CreateCustomExtension(CreateCustomExtensionRequest customExtensionRequest) throws Exception;

    //커스텀 확장자 업데이트
    public CustomExtensionVO UpdateCustomExtension(UpdateCustomExtensionRequest updateCustomExtensionRequest) throws IndexOutOfBoundsException,Exception;

    //커스텀 확장자 삭제
    public Integer DeleteCustomExtension(DeleteCustomExtensionRequest deleteCustomExtensionRequest) throws IndexOutOfBoundsException,Exception;
}
