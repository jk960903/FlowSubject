package com.example.flowsubject.controller.Interface;

import com.example.flowsubject.APIResult.APIResult;
import com.example.flowsubject.VO.CustomExtensionVO;
import com.example.flowsubject.VO.ReqeustVO.CustomExtension.CreateCustomExtensionRequest;
import com.example.flowsubject.VO.ReqeustVO.CustomExtension.DeleteCustomExtensionRequest;
import com.example.flowsubject.VO.ReqeustVO.CustomExtension.UpdateCustomExtensionRequest;

import java.util.List;

///커스텀 확장자 controller 인터페이스
public interface CustomExtensionImpl {

    //커스텀 확장자 List Read
    public APIResult<List<CustomExtensionVO>> GetCustomExtensionList();

    //커스텀 확장자  Create
    public APIResult<CustomExtensionVO> CreateCustomExtension(CreateCustomExtensionRequest createCustomExtensionRequest);

    ///커스텀 확장자 Update(추가 확장 개발) 사용 X
    public APIResult<CustomExtensionVO> UpdateCustomExtension(UpdateCustomExtensionRequest updateCustomExtensionRequest);

    ///커스텀 확장자 삭제
    public APIResult<Integer> DeleteCustomExtension(DeleteCustomExtensionRequest deleteCustomExtensionRequest);
}
