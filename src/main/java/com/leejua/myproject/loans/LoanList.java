package com.leejua.myproject.loans;

import lombok.*;
import org.springframework.context.annotation.Lazy;

@Lazy
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanList {
private Long loanBaseSeq;
private String logo, finPrdtNm, korCoNm, crdtPrdtTypeNm;
private Double crdtGradAvg;

}
