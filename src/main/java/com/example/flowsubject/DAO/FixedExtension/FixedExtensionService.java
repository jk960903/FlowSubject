package com.example.flowsubject.DAO.FixedExtension;

import com.example.flowsubject.VO.FixedExtensionVO;
import com.example.flowsubject.VO.ReqeustVO.FixedExtension.CreateFixedExtensionRequestVO;
import com.example.flowsubject.VO.ReqeustVO.FixedExtension.DeleteFixedExtensionRequestVO;
import com.example.flowsubject.VO.ReqeustVO.FixedExtension.UpdateFixedExtensionRequestVO;
import com.example.flowsubject.controller.Interface.FixedExtensionImpl;
import org.springframework.stereotype.Service;

import javax.naming.SizeLimitExceededException;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.util.List;

@Service
public class FixedExtensionService implements FixedExtensionServiceImpl {

    //고정 확장자 레포지토리
    private final FixedExtensionRepository fixedExtensionRepository;

    //고정레포지토리 주입 및 생성자
    public FixedExtensionService(FixedExtensionRepository fixedExtensionRepository){
        this.fixedExtensionRepository=fixedExtensionRepository;
    }

    //고정 확장자 Get
    public List<FixedExtensionVO> GetFixedExtensions() throws Exception{

        List<FixedExtensionVO> returnList= null;
        try{
            returnList=fixedExtensionRepository.findAll(); // 고정 확장자 전체 받아오기
        }catch(Exception e){//서버 오류

            throw new Exception("INTERNAL SERVER ERROR");
        }
        return returnList;
    }

    //고정 확장자 업데이트
    //1 checked 0 non-checked
    public FixedExtensionVO UpdateFixedExtension(UpdateFixedExtensionRequestVO updateFixedExtensionRequestVO) throws IndexOutOfBoundsException, Exception{
        FixedExtensionVO fixedExtensionVO;
        try{
            fixedExtensionVO = fixedExtensionRepository.findByIdx(updateFixedExtensionRequestVO.getIdx()).get(0); // idx를 통한 검색
            fixedExtensionVO.setIsChecked(updateFixedExtensionRequestVO.getIsChecked());//체크 현황 변경
            long time =System.currentTimeMillis();//현재 시간
            fixedExtensionVO.setChangeDate(new Date(time)); // 변경 시간 설정
            fixedExtensionVO = fixedExtensionRepository.save(fixedExtensionVO); // 변경 결과 DB 저장
        }catch(IndexOutOfBoundsException e){//idx 잘못되어 찾을수 없을 경우
            throw new IndexOutOfBoundsException("DATA NOT FOUND");
        } catch(Exception e){// 서버 오류
            throw new Exception("INTERNAL SERVER ERROR");
        }

        return fixedExtensionVO;
    }

    //고정 확장자 생성
    @Override
    public FixedExtensionVO CreateFixedExtension(CreateFixedExtensionRequestVO createFixedExtensionRequestVO) throws Exception{

        FixedExtensionVO fixedExtensionVO=null;
        long currentTime= System.currentTimeMillis(); // 현재시간
        try{
            fixedExtensionVO = FixedExtensionVO.builder().extensionname(createFixedExtensionRequestVO.getExtension_name()).isChecked(0).regDate(new Date(currentTime))
                    .changeDate(new Date(currentTime)).build(); //들어갈 데이터 생성
            fixedExtensionVO=fixedExtensionRepository.save(fixedExtensionVO); //저장
        }catch(Exception e){
            throw new Exception("INTERNAL SERVER ERROR");
        }
        return fixedExtensionVO;
    }

    //고정 확장자 삭제
    @Override
    public Integer DeleteFixedExtension(DeleteFixedExtensionRequestVO deleteFixedExtensionRequestVO) throws Exception {
        int result = 0;
        FixedExtensionVO fixedExtensionVO = null;
        try{
            fixedExtensionVO = fixedExtensionRepository.findByIdx(deleteFixedExtensionRequestVO.getIdx()).get(0); // id를 통한 검색
            fixedExtensionRepository.delete(fixedExtensionVO);//삭제
        }catch(IndexOutOfBoundsException e){//검색 불가 오류 (데이터 존재하지 않음)
            throw new IndexOutOfBoundsException("DATA NOT FOUND");
        }catch(Exception e){//서버 오류
            throw new Exception("INTERNAL SERVER ERROR");
        }
        return 1;
    }


}
