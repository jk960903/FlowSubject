package com.example.flowsubject.controller;

import com.example.flowsubject.APIResult.APIResult;
import com.example.flowsubject.APIResult.StatusEnum;
import com.example.flowsubject.DAO.FixedExtension.FixedExtensionService;
import com.example.flowsubject.VO.FixedExtensionVO;
import com.example.flowsubject.VO.ReqeustVO.FixedExtension.CreateFixedExtensionRequestVO;
import com.example.flowsubject.VO.ReqeustVO.FixedExtension.DeleteFixedExtensionRequestVO;
import com.example.flowsubject.VO.ReqeustVO.FixedExtension.UpdateFixedExtensionRequestVO;
import com.example.flowsubject.controller.Interface.FixedExtensionImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//고정 확장자 컨트롤러
@RestController
@RequestMapping(value="/api/fixed-extension")
public class FixedExtensionController implements FixedExtensionImpl {

    private final FixedExtensionService fixedExtensionService;

    public FixedExtensionController(FixedExtensionService fixedExtensionService) {
        this.fixedExtensionService = fixedExtensionService;
    }

    //고정확장자 읽기
    @Override
    @GetMapping(value="get-fixed-extension")
    public APIResult<List<FixedExtensionVO>> GetFixedExtension(HttpServletRequest request){
        List<FixedExtensionVO> list=null;
        try{
            list = fixedExtensionService.GetFixedExtensions();
        }catch(Exception e){
            return new APIResult<>(StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage(),null);
        }
        return new APIResult<>(StatusEnum.OK,"OK",list);
    }

    //고정 확장자 업데이트(추가 확장) 사용 X
    @Override
    @PatchMapping(value="update-fixed-extension")
    public APIResult<FixedExtensionVO> UpdateFixedExtension(UpdateFixedExtensionRequestVO updateFixedExtensionRequestVO, HttpServletRequest request) {
        FixedExtensionVO fixedExtensionVO = null;
        try{
            updateFixedExtensionRequestVO.CheckValidate(); // 입력값 유효성 체크
            fixedExtensionVO = fixedExtensionService.UpdateFixedExtension(updateFixedExtensionRequestVO);//업데이트
        }catch(IndexOutOfBoundsException e){ //업데이트 할 id 검색시없을 경우
            return new APIResult<>(StatusEnum.NOT_FOUND,e.getMessage(),null);
        }catch(NullPointerException e){//입력값 부재의 않을 경우
            return new APIResult<>(StatusEnum.BAD_REQUEST,e.getMessage(), null);
        }
        catch(Exception e){//서버 오류 발생한 경우
            return new APIResult<>(StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage(),null);
        }
        return new APIResult<>(StatusEnum.OK,"OK",fixedExtensionVO);
    }

    //고정확장자 삽입 (추가 확장) 현재 사용 X
    @Override
    @PostMapping(value="create-fixed-extension")
    public APIResult<FixedExtensionVO> CreateFixedExtension(CreateFixedExtensionRequestVO createFixedExtensionRequestVO, HttpServletRequest request) {
        FixedExtensionVO fixedExtensionVO;
        try{
            createFixedExtensionRequestVO.CheckValidate();//입력값 유효 검증
            fixedExtensionVO = fixedExtensionService.CreateFixedExtension(createFixedExtensionRequestVO);//고정 확장자 추가
        }catch(NullPointerException e){//입력값 부재의 없을경우
            return new APIResult<>(StatusEnum.BAD_REQUEST,e.getMessage(),null);
        }catch(IllegalArgumentException e){//입력값이 잘못 들어온 경우
            return new APIResult<>(StatusEnum.BAD_REQUEST,e.getMessage(),null);
        }catch(Exception e){// 서버 오류 경우
            return new APIResult<>(StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage(),null);
        }
        return new APIResult<>(StatusEnum.OK,"OK",fixedExtensionVO);
    }

    //고정 확장자 삭제 (추가 확장) 사용 X
    @Override
    @PatchMapping(value="delete-fixed-extension")
    public APIResult<Integer> DeleteFixedExtension(DeleteFixedExtensionRequestVO deleteFixedExtensionRequestVO, HttpServletRequest request) {
        int result = 0;
        try{
            deleteFixedExtensionRequestVO.CheckValidate();//입력값 검증
            result = fixedExtensionService.DeleteFixedExtension(deleteFixedExtensionRequestVO);//삭제 하기
        }catch(NullPointerException e){//입력값 부재의 경우
            return new APIResult<>(StatusEnum.BAD_REQUEST,e.getMessage(),0);
        }catch(IndexOutOfBoundsException e){// id를 통한 검새
            return new APIResult<>(StatusEnum.NOT_FOUND,e.getMessage(),0);
        }catch(Exception e){//서버 에러 발생의 경우
            return new APIResult<>(StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage(),0);
        }
        return new APIResult<>(StatusEnum.OK,"OK",1);
    }
}
