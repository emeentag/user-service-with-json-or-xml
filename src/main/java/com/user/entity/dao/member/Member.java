package com.user.entity.dao.member;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Member
 */
@Entity
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(columnDefinition = "VARCHAR(50)")
  private String firstName;

  @Column(columnDefinition = "VARCHAR(50)")
  private String lastName;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateOfBirth;

  @CreationTimestamp
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateCreated;

  @UpdateTimestamp
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateUpdated;

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
   * @param dateCreated the dateCreated to set
   */
  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  /**
   * @param dateUpdated the dateUpdated to set
   */
  public void setDateUpdated(Date dateUpdated) {
    this.dateUpdated = dateUpdated;
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

  /**
   * @return the dateCreated
   */
  public Date getDateCreated() {
    return dateCreated;
  }

  /**
   * @return the dateUpdated
   */
  public Date getDateUpdated() {
    return dateUpdated;
  }
}