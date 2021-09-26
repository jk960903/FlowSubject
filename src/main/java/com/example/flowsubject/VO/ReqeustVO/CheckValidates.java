package com.example.flowsubject.VO.ReqeustVO;

import javax.naming.SizeLimitExceededException;

//각 클래스마다 필수로 있어야하는 유효성 체크 인터페이스
public interface CheckValidates {

    public boolean CheckValidate() throws SizeLimitExceededException;
}
