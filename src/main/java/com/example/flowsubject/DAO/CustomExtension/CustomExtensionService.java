package com.example.flowsubject.DAO.CustomExtension;

import com.example.flowsubject.VO.CustomExtensionVO;
import com.example.flowsubject.VO.ReqeustVO.CustomExtension.CreateCustomExtensionRequest;
import com.example.flowsubject.VO.ReqeustVO.CustomExtension.DeleteCustomExtensionRequest;
import com.example.flowsubject.VO.ReqeustVO.CustomExtension.UpdateCustomExtensionRequest;
import org.springframework.stereotype.Service;

import javax.naming.SizeLimitExceededException;
import java.sql.Date;
import java.util.List;

@Service
public class CustomExtensionService implements CustomExtensionServiceImpl{

    //커스텀 확장자 레포지토리
    private final CustomExtensionRepository customExtensionRepository;

    //커스텀 서비스 생성자
    public CustomExtensionService(CustomExtensionRepository customExtensionRepository) {
        this.customExtensionRepository = customExtensionRepository;
    }

    //커스텀 확장자 List 읽기
    @Override
    public List<CustomExtensionVO> GetCustomExtensionList(){
        return customExtensionRepository.findAll();
    }

    //커스텀 확장자 중복성 체크
    @Override
    public boolean CheckCustomExtensionDuplicate(String extension_name) throws Exception{

        CustomExtensionVO customExtensionVO;
        try{
            customExtensionVO = customExtensionRepository.findCustomExtensionVOByExtensionname(extension_name).get(0); //extension_name을 통해 가져오기
        }catch(IndexOutOfBoundsException e){//검색 실패 즉 중복되지 않음
            return true;
        }catch(Exception e){//서버 에러 만생
               throw new Exception("INTERNAL SERVER ERROR");
        }

        return false;
    }

    @Override
    public CustomExtensionVO CreateCustomExtension(CreateCustomExtensionRequest createCustomExtensionRequest) throws SizeLimitExceededException,Exception{

        CustomExtensionVO customExtensionVO;
        long currentTime = System.currentTimeMillis(); // 현재 시간
        List<CustomExtensionVO> customExtensionVOList=null;
        try{
            customExtensionVOList = customExtensionRepository.findAll(); //커스텀 확장자 List 전체 가져오기
            if(customExtensionVOList.size()>=200){// 200개이상일경우 안됨
                throw new SizeLimitExceededException("커스텀 확장자의 최대 개수는 200개를 넘길수 없습니다.");
            }
            customExtensionVO= CustomExtensionVO.builder().extensionname(createCustomExtensionRequest.getExtension_name()).regdate(new Date(currentTime))
                    .changedate(new Date(currentTime)).build();//커스텀 확장자 모델 빌드
            customExtensionVO =customExtensionRepository.save(customExtensionVO); // DB 저장
        } catch(Exception e){// 서버 에러
            throw new Exception("INTERNAL SERVER ERROR");
        }
        return customExtensionVO;
    }

    @Override
    public CustomExtensionVO UpdateCustomExtension(UpdateCustomExtensionRequest updateCustomExtensionRequest) throws IndexOutOfBoundsException,Exception{
        CustomExtensionVO customExtensionVO=null;
        long current = System.currentTimeMillis();
        try{
            customExtensionVO = customExtensionRepository.findByIdx(updateCustomExtensionRequest.getIdx()).get(0);
            customExtensionVO.setExtensionname(updateCustomExtensionRequest.getExtension_name());
            customExtensionVO.setChangedate(new Date(current));
            customExtensionVO=customExtensionRepository.save(customExtensionVO);
        }catch(IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("REQUEST DATA IS NOT FOUND");
        }catch(Exception e){
            throw new Exception("INTERNAL SERVER ERROR");
        }
        return customExtensionVO;
    }

    @Override
    public Integer DeleteCustomExtension(DeleteCustomExtensionRequest deleteCustomExtensionRequest) throws IndexOutOfBoundsException,Exception{
        CustomExtensionVO customExtensionVO;
        try{
            customExtensionVO = customExtensionRepository.findByIdx(deleteCustomExtensionRequest.getIdx()).get(0);
            customExtensionRepository.delete(customExtensionVO);
        }catch(IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("REQEUST DATA IS NOT FOUND");
        }catch(Exception e){
            throw new Exception("INTERNAL SERVER ERROR");
        }
        return 1;
    }


}
