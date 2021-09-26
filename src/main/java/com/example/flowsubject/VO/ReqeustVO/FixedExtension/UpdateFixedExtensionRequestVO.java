package com.example.flowsubject.VO.ReqeustVO.FixedExtension;

import com.example.flowsubject.VO.ReqeustVO.CheckValidates;
import lombok.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//고정확장자 체크시 변경 요청 class
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateFixedExtensionRequestVO implements CheckValidates {
    private Long idx;//확장자의 idx (primary key)
    private Integer isChecked;//체크됬는지의 여부

    @Override
    public boolean CheckValidate() {//유효성 체크
        if(this.idx==null || this.idx <= 0 || this.isChecked==null || this.isChecked<0 || this.isChecked>=2){
            throw new NullPointerException("유효하지 않은 요청값입니다.");
        }

        return true;
    }
}
