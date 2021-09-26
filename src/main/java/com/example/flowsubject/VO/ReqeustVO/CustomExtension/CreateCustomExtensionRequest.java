package com.example.flowsubject.VO.ReqeustVO.CustomExtension;

import com.example.flowsubject.VO.ReqeustVO.CheckValidates;
import lombok.*;

import javax.naming.SizeLimitExceededException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//커스텀 확장자 생성 request 클래스
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCustomExtensionRequest implements CheckValidates {
    private String extension_name; // 확장자 명

    //확장자 유효성 체크
    public boolean CheckValidate() throws SizeLimitExceededException,NullPointerException,IllegalArgumentException{
        if(this.extension_name==null || this.extension_name==""){ //확장자 명이 null이거나 ""일경우
            throw new NullPointerException("확장자명은 null 이거나 공백일수 없습니다.");
        }else if(this.extension_name.length() >20){//확장자 명이 20글자보다클경우
            throw new SizeLimitExceededException("확장자명은 20자리를 넘어갈 수 없습니다.");
        }
        //고정확장자에 있는 데이터 걸러내기
        if(this.extension_name.equals("bat")||this.extension_name.equals("cmd")||this.extension_name.equals("com")
                ||this.extension_name.equals("cpl")||this.extension_name.equals("exe")||this.extension_name.equals("scr") ||this.extension_name.equals("js")){
            throw new IllegalArgumentException("고정 확장자에 있는 데이터는 들어갈 수 없습니다.");
        }
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");//확장자명에 알파벳과 숫자만 들어갈수 있도록 정의
        Matcher matcher = pattern.matcher(this.extension_name);
        if(!matcher.find()){
            throw new IllegalArgumentException("확장자 명에는 알파벳을 제외한 글자는 들어갈 수 없습니다.");
        }
        return true;
    }
}
