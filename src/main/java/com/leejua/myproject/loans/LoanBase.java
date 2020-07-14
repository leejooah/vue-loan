package com.leejua.myproject.loans;

import lombok.*;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
@Lazy
@Getter
@Setter
@ToString
@Entity(name="loanBase")
@NoArgsConstructor
public class LoanBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanBaseSeq;
    @Column(length = 10) @NotNull
    private  String dclsMonth;
    @Column(length = 10) @NotNull
    private  String finCoNo;
    @Column(length = 30)@NotNull
    private  String finPrdtCd;
    @Column(length = 4) @NotNull
    private  String crdtPrdtType;
    @Column(length = 20) @NotNull
    private  String korCoNm;
    @Column(length = 20) @NotNull
    private  String finPrdtNm;
    @Column(length = 40) @NotNull
    private  String joinWay;
    @Column(length = 10)
    private  String cbName;
    @Column(length = 20) @NotNull
    private  String crdtPrdtTypeNm;
    @Column(length = 20) @NotNull
    private  String dclsStrtDay;
    @Column(length = 10)
    private  String dclsEndDay;
    @Column(length = 20) @NotNull
    private String finCoSubmDay;
    @Column(length = 500)
    private String logo;

@Builder

public LoanBase(
       String dclsMonth, String finCoNo, String finPrdtCd,
   String crdtPrdtType, String korCoNm, String finPrdtNm,
      String joinWay, String cbName, String crdtPrdtTypeNm,
  String dclsStrtDay, String dclsEndDay, String finCoSubmDay,
       String logo

){
this.dclsMonth = dclsMonth; this.finCoNo =  finCoNo; this.finPrdtCd = finPrdtCd;
    this.crdtPrdtType = crdtPrdtType; this.korCoNm = korCoNm; this.finPrdtNm = finPrdtNm;
    this.joinWay= joinWay; this.cbName= cbName; this.crdtPrdtTypeNm = crdtPrdtTypeNm;
    this.dclsStrtDay = dclsStrtDay; this.dclsEndDay=dclsEndDay; this.finCoSubmDay=finCoSubmDay;
    this.logo = logo;

}

@OneToMany(mappedBy = "loanBase") private List<LoanOption> loanOptions;
}