package com.example.flowsubject.VO;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

//고정확장자 접근 Entity VO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="fixed_extension")
public class FixedExtensionVO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;//Privary Key And 자동 증가 id

    @Column(name="extension_name")
    private String extensionname;//확장자 이름

    @Column(name="ischecked")
    private Integer isChecked;//체크됬는지 여부 1 체크 0 안됨

    @Column(name="regdate")
    private Date regDate; //등록날짜

    @Column(name="changedate")
    private Date changeDate;//변경 날짜
}
