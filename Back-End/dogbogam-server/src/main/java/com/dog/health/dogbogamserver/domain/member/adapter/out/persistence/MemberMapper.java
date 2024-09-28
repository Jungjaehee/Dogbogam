package com.dog.health.dogbogamserver.domain.member.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.member.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member toDomain(MemberEntity entity){
        return Member.builder()
                .memberId(entity.getMemberId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .nickname(entity.getNickname())
                .isDeleted(entity.getIsDeleted())
                .createAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

    public MemberEntity toEntity(Member domain){
        return MemberEntity.builder()
                .email(domain.getEmail())
                .password(domain.getPassword())
                .nickname(domain.getNickname())
                .isDeleted(domain.getIsDeleted())
                .build();
    }
}