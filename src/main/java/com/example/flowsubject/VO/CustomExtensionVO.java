package com.example.flowsubject.VO;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

//커스텀 확장자 Table 접근 Entity VO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="custom_extension")
public class CustomExtensionVO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx; //프라이머리 키 자동증가

    @Column(name="extension_name")
    private String extensionname; // 확장자 이름

    @Column(name="regdate")
    private Date regdate; // 등록 날짜

    @Column(name="changedate")
    private Date changedate;//수정 날짜

}
