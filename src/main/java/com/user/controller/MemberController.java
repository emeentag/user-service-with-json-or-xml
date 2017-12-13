package com.user.controller;

import java.util.Set;

import javax.validation.Valid;

import com.user.entity.dao.member.Member;
import com.user.entity.dto.json.member.MemberJsonDTO;
import com.user.entity.dto.xml.member.MemberXmlDTO;
import com.user.middleware.exception.ConstraintsViolationException;
import com.user.middleware.exception.EntityNotFoundException;
import com.user.service.member.MemberService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * MemberController
 */
@Controller
@RequestMapping("/member")
public class MemberController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final MemberService memberService;

  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE })
  @ResponseStatus(value = HttpStatus.CREATED)
  public void createMember(@Valid @RequestBody MemberXmlDTO memberDTO) throws ConstraintsViolationException {

    Member memberDAO = MemberXmlDTO.Map.toDao(memberDTO);
    this.memberService.createMember(memberDAO);
    this.logger.info("XML user creation request handled.");
  }

  @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
  @ResponseStatus(value = HttpStatus.CREATED)
  public void createMember(@Valid @RequestBody MemberJsonDTO memberDTO) throws ConstraintsViolationException {

    Member memberDAO = MemberJsonDTO.Map.toDao(memberDTO);
    this.memberService.createMember(memberDAO);
    this.logger.info("JSON user creation request handled.");
  }

  @GetMapping(value = "/{memberId}", produces = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<MemberJsonDTO> getJsonMember(@PathVariable Long memberId) throws EntityNotFoundException {

    Member memberDAO = this.memberService.getMember(memberId);
    MemberJsonDTO memberDTO = MemberJsonDTO.Map.toDto(memberDAO);

    return ResponseEntity.ok(memberDTO);
  }

  @GetMapping(value = "/{memberId}", produces = { MediaType.APPLICATION_XML_VALUE })
  public ResponseEntity<MemberXmlDTO> getXmlMember(@PathVariable Long memberId) throws EntityNotFoundException {

    Member memberDAO = this.memberService.getMember(memberId);
    MemberXmlDTO memberDTO = MemberXmlDTO.Map.toDto(memberDAO);

    return ResponseEntity.ok(memberDTO);
  }

  @GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<Set<MemberJsonDTO>> getJsonMembers() throws EntityNotFoundException {

    Set<MemberJsonDTO> members = MemberJsonDTO.Map.toDto(this.memberService.getMembers());

    return ResponseEntity.ok(members);
  }

  @GetMapping(value = "/all", produces = { MediaType.APPLICATION_XML_VALUE })
  public ResponseEntity<Set<MemberXmlDTO>> getXmlMembers() throws EntityNotFoundException {

    Set<MemberXmlDTO> members = MemberXmlDTO.Map.toDto(this.memberService.getMembers());

    return ResponseEntity.ok(members);
  }

  @PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
  @ResponseStatus(value = HttpStatus.OK)
  public void updateMember(@RequestBody MemberJsonDTO memberDTO)
      throws ConstraintsViolationException, EntityNotFoundException {

    logger.info(memberDTO.getId().toString());

    Member memberDAO = MemberJsonDTO.Map.toDao(memberDTO);
    this.memberService.updateMember(memberDAO);
    this.logger.info("JSON user update request handled.");

  }

  @PutMapping(consumes = { MediaType.APPLICATION_XML_VALUE })
  @ResponseStatus(value = HttpStatus.OK)
  public void updateMember(@RequestBody MemberXmlDTO memberDTO)
      throws ConstraintsViolationException, EntityNotFoundException {

    logger.info(memberDTO.getId().toString());

    Member memberDAO = MemberXmlDTO.Map.toDao(memberDTO);
    this.memberService.updateMember(memberDAO);
    this.logger.info("XML user update request handled.");

  }

  @DeleteMapping(value = "/{memberId}")
  @ResponseStatus(value = HttpStatus.OK)
  public void deleteMember(@PathVariable Long memberId) throws EntityNotFoundException {

    this.memberService.deleteMember(memberId);

    this.logger.info("User delete request handled.");

  }

}