package com.dog.health.dogbogamserver.domain.members.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberSpringDataRepository extends JpaRepository<MemberEntity, Long> {
}
