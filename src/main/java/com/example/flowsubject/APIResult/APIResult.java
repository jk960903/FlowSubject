package com.example.flowsubject.APIResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
//Controller 결과
public class APIResult<T> {
    private StatusEnum status; //HTTP 상태 코드
    private String resultMessage; //상세 메시지
    private T data; // 데이터


}
