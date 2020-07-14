package com.leejua.myproject.loans;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class LoanBaseRepositoryImpl extends QuerydslRepositorySupport implements LoanBaseRepositoryCustom {

    LoanBaseRepositoryImpl(){
       super(LoanBase.class);
    }

    @Override
    public Page<LoanList> findList(Pageable pageable, String searchWord) {
        QLoanBase ba = QLoanBase.loanBase;
        QLoanOption op = QLoanOption.loanOption;
        BooleanBuilder builder = new BooleanBuilder();
        if (!searchWord.equals("null")){
            builder.and(ba.finPrdtNm.like("%"+searchWord+"%")).or(ba.korCoNm.like("%"+searchWord+"%"))
                                .or(ba.crdtPrdtTypeNm.like("%"+searchWord+"%"));
        }
        JPQLQuery query = from(ba).leftJoin(ba.loanOptions,op).select(Projections.fields(LoanList.class,ba.logo, ba.loanBaseSeq, ba.finPrdtNm,
                ba.korCoNm, ba.crdtPrdtTypeNm, op.crdtGradAvg)).on(op.crdtLendRateType.like("%A%")).where(builder);



        List<LoanList> list = getQuerydsl().applyPagination(pageable,query).fetch();


        return new PageImpl<LoanList>(list, pageable, query.fetchCount());
    }

    @Override
    public LoanDetail findDetailById(Long loanBaseSeq) {
        QLoanBase ba = QLoanBase.loanBase;
        QLoanOption op = QLoanOption.loanOption;
        return from(ba).leftJoin(ba.loanOptions,op).select(Projections.fields(LoanDetail.class, ba.loanBaseSeq, ba.logo, ba.finPrdtNm, ba.korCoNm, ba.crdtPrdtTypeNm, ba.joinWay,
                ba.dclsStrtDay,ba.dclsEndDay,op.crdtLendRateTypeNm,op.crdtGrad1,op.crdtGrad4, op.crdtGrad5,op.crdtGrad6,op.crdtGrad10,op.crdtGradAvg)).
                on(op.crdtLendRateType.like("%A%")).where(ba.loanBaseSeq.eq(loanBaseSeq)).fetchOne();

    }

}
