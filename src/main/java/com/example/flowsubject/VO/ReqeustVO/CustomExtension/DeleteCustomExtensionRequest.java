package com.example.flowsubject.VO.ReqeustVO.CustomExtension;

import com.example.flowsubject.VO.ReqeustVO.CheckValidates;
import lombok.*;


//커스텀 확장자 삭제 요청 클래스
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteCustomExtensionRequest implements CheckValidates {
    private Long idx; //커스텀 확장자의 idx (primary key)

    //유효성 검사
    @Override
    public boolean CheckValidate() throws NullPointerException{
        if(this.idx == null || this.idx <0){ // idx 가 null이거나 0 보다작은 경우
            throw new NullPointerException("BAD REQUEST");
        }
        return true;
    }
}
