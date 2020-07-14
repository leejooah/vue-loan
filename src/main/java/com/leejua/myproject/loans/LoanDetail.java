package com.leejua.myproject.loans;

import lombok.*;
import org.springframework.context.annotation.Lazy;

@Lazy
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanDetail {
    private Long loanBaseSeq;
    private String logo, finPrdtNm, korCoNm, crdtPrdtTypeNm, joinWay,
                               dclsStrtDay,dclsEndDay,crdtLendRateTypeNm;
    private Double crdtGrad1,crdtGrad4, crdtGrad5,crdtGrad6,crdtGrad10,crdtGradAvg;
}
