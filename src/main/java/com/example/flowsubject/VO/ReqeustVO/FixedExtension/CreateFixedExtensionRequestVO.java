package com.example.flowsubject.VO.ReqeustVO.FixedExtension;

import com.example.flowsubject.VO.ReqeustVO.CheckValidates;
import lombok.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
* 웹에서는 직접적으로 보이지 않고 추후 backoffice페이지 작업이나 혹은 직접 DB 작업
* 고정확장자 생성 요청 클래스
* */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateFixedExtensionRequestVO implements CheckValidates {
    private String extension_name; //확장자 이름

    //유효성 체크
    @Override
    public boolean CheckValidate() throws NullPointerException,IllegalArgumentException{
        if(this.extension_name == null || this.extension_name.equals("")){ // 확자자명이 null이거나 공백일경우
            throw new NullPointerException("유효하지 않은 요청값입니다.");
        }
        if(this.extension_name.length() >20){//확장자 이름 길이가 20자리 이상일경우
            throw new IllegalArgumentException("요청 길이는 20자리를 초과할수 없습니다.");
        }
        if(this.extension_name.equals("bat")||this.extension_name.equals("cmd")||this.extension_name.equals("com")
                ||this.extension_name.equals("cpl")||this.extension_name.equals("exe")||this.extension_name.equals("scr") ||this.extension_name.equals("js")){
            //확장자 이름이 고정확장자 안에있을경우
            throw new IllegalArgumentException("고정 확장자에 있는 데이터는 들어갈 수 없습니다.");
        }
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$"); // 알파벳 숫자만 들어올수 있도록
        Matcher matcher = pattern.matcher(this.extension_name);
        if(!matcher.find()){// 다른 문자 들어왔을 경우
            throw new IllegalArgumentException("확장자 명에는 알파벳과 숫자를 제외한 글자는 들어갈 수 없습니다.");
        }
        return true;
    }

    //insert table values = (extension_name,1,now(),now())
    //idx 는 auto increment primary key
    //ischecked는 1이 체크됨 0이 안됨
    //등록날자와 변경날자는 처음 입력이므로 now() 값
}
