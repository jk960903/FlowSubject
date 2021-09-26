package com.example.flowsubject.VO.ReqeustVO.CustomExtension;

import com.example.flowsubject.VO.ReqeustVO.CheckValidates;
import lombok.*;

import javax.naming.SizeLimitExceededException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

///필요할지는 의문이지만 기본적으로 CRUD에 대한것은 구현해야하므로 구현하였습니다.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCustomExtensionRequest implements CheckValidates {
    private Long idx; //커스텀 확장자 idx

    private String extension_name;//커스텀 확장자 이름

    //유효성 체크
    @Override
    public boolean CheckValidate() throws SizeLimitExceededException,NullPointerException,IllegalArgumentException{
        if(this.idx==null || this.idx <= 0 || this.extension_name == null || this.extension_name.equals("")){ // null이거나 공백 혹은 idx가 0보다 작을경우
            throw new NullPointerException("유효하지 않은 요청값입니다.");
        }
        if(this.extension_name.length()>20){//확장자 명이 20자리보다 길경우
            throw new SizeLimitExceededException("커스텀 확장자의 최대 길이는 20까지입니다.");
        }
        Pattern pattern = Pattern.compile("\t^[a-zA-Z0-9]*$");//알파벳 또는 숫자만
        Matcher matcher = pattern.matcher(this.extension_name);
        if(!matcher.find()){//제외한 것이 들어가있을 경우
            throw new IllegalArgumentException("확장자 명에는 알파벳과 숫자를 제외한 글자는 들어갈 수 없습니다.");
        }
        return true;
    }
}
