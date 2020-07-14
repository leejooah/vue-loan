package com.leejua.myproject.members;

import lombok.*;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Lazy
@Setter
@Getter
@ToString
@Entity(name="member")
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberSeq;
    @Column(length = 30) @NotNull
    private String email;
    @Column(length = 20) @NotNull
    private String passwd;

    @Builder
    public Member(String email, String passwd){
        this.email = email; this.passwd = passwd;
    }
}
