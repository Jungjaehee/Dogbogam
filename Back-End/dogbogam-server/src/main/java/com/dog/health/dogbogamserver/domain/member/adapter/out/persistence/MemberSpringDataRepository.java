package com.dog.health.dogbogamserver.domain.member.adapter.out.persistence;

import aj.org.objectweb.asm.commons.Remapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberSpringDataRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByMemberId(Long memberId);

    Optional<MemberEntity> findByEmail(String email);
}
