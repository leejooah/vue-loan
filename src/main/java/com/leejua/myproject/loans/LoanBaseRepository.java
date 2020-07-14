package com.leejua.myproject.loans;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanBaseRepository extends JpaRepository<LoanBase, Long> , LoanBaseRepositoryCustom{

    @Modifying(clearAutomatically=true, flushAutomatically=true)
    @Query(value="update loan_base l set l.logo = :logo where l.kor_co_nm like %:korCoNm%", nativeQuery=true)
    public void updateLogo(@Param("korCoNm")String korCoNm, @Param("logo")String logo);


}
