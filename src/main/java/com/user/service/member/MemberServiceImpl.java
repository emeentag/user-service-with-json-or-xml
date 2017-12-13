package com.user.service.member;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.user.entity.dao.member.Member;
import com.user.middleware.exception.ConstraintsViolationException;
import com.user.middleware.exception.EntityNotFoundException;
import com.user.repository.member.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MemberService
 */
@Service
public class MemberServiceImpl implements MemberService {

  @Autowired
  private MemberRepository memberRepository;

  @Override
  public Member getMember(Long id) throws EntityNotFoundException {

    Member member = this.memberRepository.findOne(id);

    if (member == null) {
      throw new EntityNotFoundException("There is no user with this id: " + id);
    }

    return member;
  }

  @Override
  public Set<Member> getMembers() throws EntityNotFoundException {

    List<Member> members = this.memberRepository.findAll();

    if (members.isEmpty()) {
      throw new EntityNotFoundException("There is no user yet.");
    }

    Set<Member> setMembers = members.stream().collect(Collectors.toSet());

    return setMembers;
  }

  @Override
  public Member createMember(Member member) throws ConstraintsViolationException {
    try {
      this.memberRepository.save(member);
    } catch (Exception e) {
      throw new ConstraintsViolationException("Something went wrong while creating user.");
    }

    return member;
  }

  @Override
  public Member updateMember(Member member) throws ConstraintsViolationException, EntityNotFoundException {

    Member dbMember = this.memberRepository.findOne(member.getId());

    if (dbMember == null) {
      throw new EntityNotFoundException("There is no user with this id to update.");
    }

    this.memberRepository.save(member);

    return member;
  }

  @Override
  public Long deleteMember(Long id) throws EntityNotFoundException {

    Member dbMember = this.memberRepository.findOne(id);

    if (dbMember == null) {
      throw new EntityNotFoundException("There is no user with this id to delete.");
    }

    this.memberRepository.delete(dbMember);

    return id;
  }

}