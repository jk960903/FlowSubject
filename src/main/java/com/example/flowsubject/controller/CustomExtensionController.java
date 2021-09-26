package com.example.flowsubject.controller;

import com.example.flowsubject.APIResult.APIResult;
import com.example.flowsubject.APIResult.StatusEnum;
import com.example.flowsubject.DAO.CustomExtension.CustomExtensionService;
import com.example.flowsubject.VO.CustomExtensionVO;
import com.example.flowsubject.VO.ReqeustVO.CustomExtension.CreateCustomExtensionRequest;
import com.example.flowsubject.VO.ReqeustVO.CustomExtension.DeleteCustomExtensionRequest;
import com.example.flowsubject.VO.ReqeustVO.CustomExtension.UpdateCustomExtensionRequest;
import com.example.flowsubject.controller.Interface.CustomExtensionImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.SizeLimitExceededException;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value="/api/custom-extension")
public class CustomExtensionController implements CustomExtensionImpl {

    //커스텀 확장자 서비스
    private final CustomExtensionService customExtensionService;

    ///생성자
    public CustomExtensionController(CustomExtensionService customExtensionService) {
        this.customExtensionService = customExtensionService;
    }

    //커스텀 확장자 Read
    @Override
    @GetMapping(value="/get-custom-extension")
    public APIResult<List<CustomExtensionVO>> GetCustomExtensionList() {
        List<CustomExtensionVO> customExtensionVOList =null;
        try{
            customExtensionVOList=customExtensionService.GetCustomExtensionList(); //DB로 부터 데이터 가져오기
        }catch(Exception e){ // 데이터가 없어도 Null이 아닌 empty리스트로 넘어오기 때문에 걸린다면 서버쪽 에러가 생긴것
            return new APIResult<>(StatusEnum.INTERNAL_SERVER_ERROOR,"INTERNAL SERVER ERROR",null);
        }
        return new APIResult<>(StatusEnum.OK,"OK",customExtensionVOList);
    }

    //커스텀 확장자 생성
    @Override
    @PostMapping(value="create-custom-extension")
    public APIResult<CustomExtensionVO> CreateCustomExtension(CreateCustomExtensionRequest createCustomExtensionRequest) {
        CustomExtensionVO customExtensionVO;
        try{
            //입력값 유효 체크
            createCustomExtensionRequest.CheckValidate();
            //입력값이 중복된 것인지 체크
            if(customExtensionService.CheckCustomExtensionDuplicate(createCustomExtensionRequest.getExtension_name())){
                customExtensionVO = customExtensionService.CreateCustomExtension(createCustomExtensionRequest); // 커스텀 확장자 insert
            }else{
                //중복되었을 경우 false로 리턴되어 return
                return new APIResult<>(StatusEnum.BAD_REQUEST,"이미등록된 확장자 명입니다.",null);
            }

        }catch(NullPointerException e){//유효 체크에서 extension_name이 null이거나 공백일경우
            return new APIResult<>(StatusEnum.BAD_REQUEST,e.getMessage(),null);
        }catch(IllegalArgumentException e){//특수문자가 들어갔을경우
            return new APIResult<>(StatusEnum.BAD_REQUEST,e.getMessage(),null);
        }catch(SizeLimitExceededException e){//최대 한도가 200개로 명시되어 200개를 넘었을경우 exception
            return new APIResult<>(StatusEnum.BAD_REQUEST,e.getMessage(),null);
        } catch(Exception e){//서버쪽 에러
            return new APIResult<>(StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage(),null);
        }
        return new APIResult<>(StatusEnum.OK,"OK",customExtensionVO);
    }
    
    ///업데이트 커스텀 확장자
    @Override
    @PatchMapping(value="update-custom-extension")
    public APIResult<CustomExtensionVO> UpdateCustomExtension(UpdateCustomExtensionRequest updateCustomExtensionRequest) {
        CustomExtensionVO customExtensionVO=null;
        try{
            updateCustomExtensionRequest.CheckValidate();
            if(customExtensionService.CheckCustomExtensionDuplicate(updateCustomExtensionRequest.getExtension_name())){
                customExtensionVO = customExtensionService.UpdateCustomExtension(updateCustomExtensionRequest);
            }
            else{
                return new APIResult<>(StatusEnum.BAD_REQUEST,"중복된 데이터입니다.",null);
            }

        }catch(NullPointerException e){//IDX값이 잘못되었거나 extensionname이 null or 공백인경우
            return new APIResult<>(StatusEnum.BAD_REQUEST,e.getMessage(),null);
        }catch(IllegalArgumentException e){//커스텀 확장자의 형식이 잘못되었을경우
            return new APIResult<>(StatusEnum.BAD_REQUEST,e.getMessage(),null);
        }catch(IndexOutOfBoundsException e){//IDX값이 잘못되어 검색에 잘못되었을 경우
            return new APIResult<>(StatusEnum.NOT_FOUND,e.getMessage(),null);
        }catch(SizeLimitExceededException e){//extension_name 이 20글자를 넘었을경우
            return new APIResult<>(StatusEnum.BAD_REQUEST,e.getMessage(),null);
        }catch(Exception e){//서버에러
            return new APIResult<>(StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage(),null);
        }
        return new APIResult<>(StatusEnum.OK,"OK",customExtensionVO);
    }


    //커스텀 확장자 삭제
    @Override
    @DeleteMapping(value="delete-custom-extension")
    public APIResult<Integer> DeleteCustomExtension(DeleteCustomExtensionRequest deleteCustomExtensionRequest) {
        int result = 0;
        try{
            ///유효성 확인
            deleteCustomExtensionRequest.CheckValidate();
            result=customExtensionService.DeleteCustomExtension(deleteCustomExtensionRequest); /// 커스텀 확장자 삭제
        }catch(NullPointerException e){ /// 유효성에서 idx결과가 잘못된 경우
            return new APIResult<>(StatusEnum.BAD_REQUEST,e.getMessage(),0);
        } catch (IndexOutOfBoundsException e) { //IDX가 잘못되어 없는 것을 검색한 경우
            return new APIResult<>(StatusEnum.NOT_FOUND,e.getMessage(),0);
        }catch(Exception e){//서버에러
            return new APIResult<>(StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage(),0);
        }
        return new APIResult<>(StatusEnum.OK,"OK",1);
    }
}
