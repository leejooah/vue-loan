package com.leejua.myproject.loans;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Lazy
@Getter
@Setter
@ToString(exclude = "loanBase")
@Entity(name = "loanOption")
@NoArgsConstructor
public class LoanOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanOptionSeq;
    @Column(length =10) @NotNull
    private String dclsMonth;
    @Column(length = 10) @NotNull
    private String finCoNo;
    @Column(length = 30) @NotNull
    private String finPrdtCd;
    @Column(length = 4) @NotNull
    private String crdtPrdtType;
    @Column(length = 4) @NotNull
    private String crdtLendRateType;
    @Column(length = 10) @NotNull
    private String crdtLendRateTypeNm;
    @Column @NotNull
    private double crdtGrad1;
    @Column @NotNull
    private double crdtGrad4;
    @Column @NotNull
    private double crdtGrad5;
    @Column @NotNull
    private double crdtGrad6;
    @Column @NotNull
    private double crdtGrad10;
    @Column @NotNull
    private double crdtGradAvg;

    @Builder
    public LoanOption(
            String dclsMonth, String finCoNo, String finPrdtCd,
            String crdtPrdtType, String crdtLendRateType, String crdtLendRateTypeNm,
            double crdtGrad1, double crdtGrad4, double crdtGrad5,
            double crdtGrad6, double crdtGrad10, double crdtGradAvg
            ){this.dclsMonth = dclsMonth; this.finCoNo =  finCoNo; this.finPrdtCd = finPrdtCd;
        this.crdtPrdtType = crdtPrdtType;this.crdtLendRateType = crdtLendRateType;
        this.crdtLendRateTypeNm = crdtLendRateTypeNm;
        this.crdtGrad1 = crdtGrad1; this.crdtGrad4 = crdtGrad4; this.crdtGrad5 = crdtGrad5;
        this.crdtGrad6 = crdtGrad6; this.crdtGrad10 = crdtGrad10; this.crdtGradAvg = crdtGradAvg;

    }


    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "loan_base_seq") private LoanBase loanBase;
}
