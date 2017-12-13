package com.user.entity.dto.xml.member;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.user.entity.dao.member.Member;

/**
 * MemberJsonDTO
 */
@JacksonXmlRootElement(localName = "member")
@XmlAccessorType(XmlAccessType.FIELD)
public class MemberXmlDTO {

  @XmlAttribute(name = "id")
  private Long id;

  @XmlElement(name = "firstName")
  private String firstName;

  @XmlElement(name = "lastName")
  private String lastName;

  @XmlElement(name = "dateOfBirth")
  @JsonFormat(pattern = "dd-MM-yyyy")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date dateOfBirth;

  public MemberXmlDTO() {
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

    public static Member toDao(MemberXmlDTO dto) {
      Member member = new Member();
      member.setId(dto.getId());
      member.setFirstName(dto.getFirstName());
      member.setLastName(dto.getLastName());
      member.setDateOfBirth(dto.getDateOfBirth());

      return member;
    }

    public static MemberXmlDTO toDto(Member dao) {
      MemberXmlDTO member = new MemberXmlDTO();
      member.setId(dao.getId());
      member.setFirstName(dao.getFirstName());
      member.setLastName(dao.getLastName());
      member.setDateOfBirth(dao.getDateOfBirth());

      return member;
    }

    public static Set<MemberXmlDTO> toDto(Set<Member> members) {

      Set<MemberXmlDTO> memberDTOs = members.stream().map((member) -> {
        return toDto(member);
      }).collect(Collectors.toSet());

      return memberDTOs;
    }
  }

}