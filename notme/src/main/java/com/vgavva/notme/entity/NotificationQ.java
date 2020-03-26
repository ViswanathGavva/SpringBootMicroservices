package com.vgavva.notme.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
@Table(name = "notfn_q",schema="notme")

public class NotificationQ extends Auditable<String> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1736742883120394075L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER,optional = false)
	@JoinColumn(name = "notfn_type_id",nullable = false)		
	private NotificationType notfnType;
	
	@ManyToOne(fetch = FetchType.EAGER,optional = false)
	@JoinColumn(name = "delivery_chnl_id",nullable = false)	
	private DeliveryChannel deliveryChannel;
	
	@Column(name="header",columnDefinition = "text")
	private String header;
	
	@Column(name="content",columnDefinition = "text")
	private String content;
	
	@Column(name="recpnt_p",columnDefinition = "text")
	private String primaryReceipent;
	
	@Column(name="recpnt_s",columnDefinition = "text")
	private String secondaryReceipent;
	
	@Column(name="recpnt_o",columnDefinition = "text")
	private String otherReceipent;
	
	@Column(name="status")
	private String status;
				
}
