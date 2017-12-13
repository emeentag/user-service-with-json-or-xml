package com.user.service.member;

import java.util.Set;

import com.user.entity.dao.member.Member;
import com.user.middleware.exception.ConstraintsViolationException;
import com.user.middleware.exception.EntityNotFoundException;

/**
 * MemberServiceImpl
 */
public interface MemberService {

  public Member getMember(Long id) throws EntityNotFoundException;

  public Set<Member> getMembers() throws EntityNotFoundException;

  public Member createMember(Member member) throws ConstraintsViolationException;

  public Member updateMember(Member member) throws ConstraintsViolationException, EntityNotFoundException;

  public Long deleteMember(Long id) throws EntityNotFoundException;

}