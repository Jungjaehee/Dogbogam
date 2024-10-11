package com.dog.health.dogbogamserver.global.auth.dto;

import com.dog.health.dogbogamserver.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Getter
@AllArgsConstructor
public class MemberPrincipal implements UserDetails {

    private Long memberId;
    private String email;
    private String password;

    @Builder
    private MemberPrincipal(String email, Long memberId, String password) {
        this.email = email;
        this.memberId = memberId;
        this.password = password;
    }

    public static MemberPrincipal createMemberAuthority(Member member) {
        return MemberPrincipal.builder()
                .email(member.getEmail())
                .memberId(member.getMemberId())
                .password(member.getPassword())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
