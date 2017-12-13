package com.user.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.dao.member.Member;

/**
 * MemberRepository
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}