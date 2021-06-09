package com.example.jpamultitenancy.repository;

import com.example.jpamultitenancy.domain.TenantMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantMemberRepository extends JpaRepository<TenantMember, Long> {}
