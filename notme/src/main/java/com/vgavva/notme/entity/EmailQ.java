package com.vgavva.notme.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
@Table(name = "email_q",schema="notme")
public class EmailQ extends Auditable<String> {	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5172989936998611675L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "notfn_id",nullable = false)		
	private NotificationQ notfn;		
	
	@Column(name="subject",columnDefinition = "text")
	private String subject;
	
	@Column(name="body",columnDefinition = "text")
	private String body;
	
	@Column(name="to_email",columnDefinition = "text")
	private String toEmail;
	
	@Column(name="cc_email",columnDefinition = "text")
	private String ccEmail;
	
	@Column(name="bcc_email",columnDefinition = "text")
	private String bccEmail;
	
	@Column(name="status")
	private String status;

}
