package com.example.flowsubject.VO.ReqeustVO.FixedExtension;

import com.example.flowsubject.VO.ReqeustVO.CheckValidates;
import lombok.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//고정 확장자 삭제 요청
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteFixedExtensionRequestVO implements CheckValidates {
    private Long idx; //확장자의 idx (primary key)

    //유효성 체크
    @Override
    public boolean CheckValidate() throws NullPointerException{
        if(this.idx==null || this.idx <= 0 ){
            throw new NullPointerException("유효하지 않은 요청값입니다.");
        }

        return true;
    }
}
