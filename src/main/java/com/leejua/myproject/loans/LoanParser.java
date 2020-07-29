package com.leejua.myproject.loans;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.net.URL;

@Service("parser") @Lazy
public class LoanParser {
@Autowired LoanBaseRepository loanBaseRepository;
@Autowired LoanOptionRepository loanOptionRepository;


    public void parse(){
try {
        URL url = new URL("http://finlife.fss.or.kr/finlifeapi/creditLoanProductsSearch.json?auth=122aca02f52721055b4eb56ad750a3a8&topFinGrpNo=020000&pageNo=1");
        InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
        JsonObject object = (JsonObject) JsonParser.parseReader(isr);
    JsonObject result = (JsonObject) object.get("result");
    JsonArray baseList = (JsonArray) result.get("baseList");
        JsonArray optionList = (JsonArray) result.get("optionList");
for(int i=0; i<=baseList.size()-1; i++) {
    JsonObject baseElement = (JsonObject) baseList.get(i);
    LoanBase loanBase = new LoanBase();
    loanBase.setFinCoNo(baseElement.get("fin_co_no").toString());
    loanBase.setFinPrdtCd(baseElement.get("fin_prdt_cd").toString());
    loanBase.setCrdtPrdtType(baseElement.get("crdt_prdt_type").toString());
    loanBase.setKorCoNm(baseElement.get("kor_co_nm").toString());
    loanBase.setFinPrdtNm(baseElement.get("fin_prdt_nm").toString());
    loanBase.setJoinWay(baseElement.get("join_way").toString());
    loanBase.setCbName(baseElement.get("cb_name").toString() );
    loanBase.setCrdtPrdtTypeNm(baseElement.get("crdt_prdt_type_nm").toString());
    loanBase.setDclsStrtDay(baseElement.get("dcls_strt_day").toString());
    loanBase.setDclsEndDay(baseElement.get("dcls_end_day").toString());
    loanBase.setFinCoSubmDay(baseElement.get("fin_co_subm_day").toString());
loanBaseRepository.save(loanBase);
}
LoanOption loanOption = null;

    for(int i=0; i<=optionList.size()-1; i++){
        loanOption = new LoanOption();
        JsonObject optionElement = (JsonObject) optionList.get(i);
        loanOption.setDclsMonth(optionElement.get("dcls_month").toString());
    loanOption.setFinCoNo(optionElement.get("fin_co_no").toString());
    loanOption.setFinPrdtCd(optionElement.get("fin_prdt_cd").toString());
    loanOption.setCrdtPrdtType(optionElement.get("crdt_prdt_type").toString());
    loanOption.setCrdtLendRateType(optionElement.get("crdt_lend_rate_type").toString());
    loanOption.setCrdtLendRateTypeNm(optionElement.get("crdt_lend_rate_type_nm").toString());
    loanOption.setCrdtGrad1(optionElement.get("crdt_grad_1").isJsonNull()?0 :Double.parseDouble(optionElement.get("crdt_grad_1").toString()));
    loanOption.setCrdtGrad4(optionElement.get("crdt_grad_4").isJsonNull()?0 :Double.parseDouble(optionElement.get("crdt_grad_4").toString()));
    loanOption.setCrdtGrad5(optionElement.get("crdt_grad_5").isJsonNull()?0 :Double.parseDouble(optionElement.get("crdt_grad_5").toString()));
    loanOption.setCrdtGrad6(optionElement.get("crdt_grad_6").isJsonNull()?0 :Double.parseDouble(optionElement.get("crdt_grad_6").toString()));
    loanOption.setCrdtGrad10(optionElement.get("crdt_grad_10").isJsonNull()?0 : Double.parseDouble(optionElement.get("crdt_grad_10").toString()));
    loanOption.setCrdtGradAvg(Double.parseDouble(optionElement.get("crdt_grad_avg").toString()) );
    for (int j=1; j<=baseList.size();j++){
        if (loanOption.getFinCoNo().equals(loanBaseRepository.findById((long)j).get().getFinCoNo())
        && loanOption.getFinPrdtCd().equals(loanBaseRepository.findById((long)j).get().getFinPrdtCd())
        && loanOption.getCrdtPrdtType().equals(loanBaseRepository.findById((long) j).get().getCrdtPrdtType())) {
            loanOption.setLoanBase(loanBaseRepository.findById((long)j).get());
        }
    }
           loanOptionRepository.save(loanOption);
 }

}catch (Exception e){
    System.out.println("http 연결 에러");
    e.printStackTrace();
}


        }
        public void imageUploader(){
            loanBaseRepository.updateLogo("우리은행", "https://cdn.finda.co.kr/images/bank/bank_symbol_woori.png");
            loanBaseRepository.updateLogo("한국스탠다드차타드은행","https://cdn.finda.co.kr/images/bank/bank_symbol_sc.png");
            loanBaseRepository.updateLogo("한국씨티은행","https://cdn.finda.co.kr/images/bank/bank_symbol_citi.png");
            loanBaseRepository.updateLogo("대구은행","https://cdn.finda.co.kr/images/bank/bank_symbol_dgb.png");
            loanBaseRepository.updateLogo("부산은행","https://cdn.finda.co.kr/images/bank/bank_symbol_bnkbs.png");
            loanBaseRepository.updateLogo("광주은행","https://cdn.finda.co.kr/images/bank/bank_symbol_gwangju.png");
            loanBaseRepository.updateLogo("제주은행","https://cdn.finda.co.kr/images/bank/bank_symbol_jeju.png");
            loanBaseRepository.updateLogo("전북은행","https://cdn.finda.co.kr/images/bank/bank_symbol_jbb.png");
            loanBaseRepository.updateLogo("경남은행","https://cdn.finda.co.kr/images/bank/bank_symbol_bnkgn.png");
            loanBaseRepository.updateLogo("중소기업은행","https://cdn.finda.co.kr/images/bank/bank_symbol_ibk.png");
            loanBaseRepository.updateLogo("한국산업은행","https://cdn.finda.co.kr/images/bank/bank_symbol_kdb.png");
            loanBaseRepository.updateLogo("국민은행","https://cdn.finda.co.kr/images/bank/bank_symbol_kb.png");
            loanBaseRepository.updateLogo("신한은행","https://cdn.finda.co.kr/images/bank/bank_symbol_shinhan_cd.png");
            loanBaseRepository.updateLogo("농협은행주식회사","https://cdn.finda.co.kr/images/bank/bank_symbol_nh.png");
            loanBaseRepository.updateLogo("하나은행","https://cdn.finda.co.kr/images/bank/bank_symbol_hana_cpt.png");
            loanBaseRepository.updateLogo("주식회사 케이뱅크은행","https://cdn.finda.co.kr/images/bank/bank_symbol_kbank.png");
            loanBaseRepository.updateLogo("수협은행","https://cdn.finda.co.kr/images/bank/bank_symbol_suhyup.png");
            loanBaseRepository.updateLogo("주식회사 카카오뱅크","https://cdn.finda.co.kr/images/bank/bank_symbol_kakaobank.png");
                }









}
