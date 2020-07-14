package com.leejua.myproject.loans;


import com.querydsl.core.QueryResults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoanBaseRepositoryCustom {
    public Page<LoanList> findList(Pageable pageable, String searchWord);
    public LoanDetail findDetailById(Long loanBaseSeq);
}
