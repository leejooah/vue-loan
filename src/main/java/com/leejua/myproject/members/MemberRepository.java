package com.leejua.myproject.members;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Transactional
    @Modifying
public void deleteByEmail(String email);
    @Transactional
    @Modifying(clearAutomatically=true, flushAutomatically=true)
    @Query(value="update member m set m.passwd = :passwd where m.email like :email" , nativeQuery=true)
    public void updatePasswd(@Param("email")String email,@Param("passwd")String passwd);

}
