package com.user.entity.dto.json.member;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.user.entity.dao.member.Member;

/**
 * MemberJsonDTO
 */
@JsonInclude(value = Include.NON_NULL)
public class MemberJsonDTO {

  @JsonProperty(value = "id")
  private Long id;

  @JsonProperty(value = "firstName")
  private String firstName;

  @JsonProperty(value = "lastName")
  private String lastName;

  @JsonProperty(value = "dateOfBirth")
  @JsonFormat(pattern = "dd-MM-yyyy")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date dateOfBirth;

  public MemberJsonDTO() {
    super();
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @param firstName the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @param lastName the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @param dateOfBirth the dateOfBirth to set
   */
  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @return the dateOfBirth
   */
  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public static class Map {

    public static Member toDao(MemberJsonDTO dto) {
      Member member = new Member();
      member.setId(dto.getId());
      member.setFirstName(dto.getFirstName());
      member.setLastName(dto.getLastName());
      member.setDateOfBirth(dto.getDateOfBirth());

      return member;
    }

    public static MemberJsonDTO toDto(Member dao) {
      MemberJsonDTO member = new MemberJsonDTO();
      member.setId(dao.getId());
      member.setFirstName(dao.getFirstName());
      member.setLastName(dao.getLastName());
      member.setDateOfBirth(dao.getDateOfBirth());

      return member;
    }

    public static Set<MemberJsonDTO> toDto(Set<Member> members) {

      Set<MemberJsonDTO> memberDTOs = members.stream().map((member) -> {
        return toDto(member);
      }).collect(Collectors.toSet());

      return memberDTOs;
    }
  }

}