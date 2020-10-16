package com.user.userProfile.modal;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuditDetails {
	private Long auditId;
	private String userId;
	private String eventName;
	private Integer buId;
	private Integer subBuId;
	private Integer applicationId;
	private String environment;
	private String moduleName;
	private String entityName;
	private String entityActionName;
	private String auditData;
	private Integer actionBy;
	private String userlogin;
	private String issuer;
	public String getUserlogin() {
		return userlogin;
	}

	public void setUserlogin(String userlogin) {
		this.userlogin = userlogin;
	}



	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Phoenix")
	private Date auditDateTime;

	public Long getAuditId() {
		return auditId;
	}

	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	
	public Integer getBuId() {
		return buId;
	}

	public void setBuId(Integer buId) {
		this.buId = buId;
	}

	

	public Integer getSubBuId() {
		return subBuId;
	}

	public void setSubBuId(Integer subBuId) {
		this.subBuId = subBuId;
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityActionName() {
		return entityActionName;
	}

	public void setEntityActionName(String entityActionName) {
		this.entityActionName = entityActionName;
	}

	public String getAuditData() {
		return auditData;
	}

	public void setAuditData(String auditData) {
		this.auditData = auditData;
	}

	public Integer getActionBy() {
		return actionBy;
	}

	public void setActionBy(Integer actionBy) {
		this.actionBy = actionBy;
	}

	public Date getAuditDateTime() {
		return auditDateTime;
	}

	public void setAuditDateTime(Date auditDateTime) {
		this.auditDateTime = auditDateTime;
	}

	@Override
	public String toString() {
		return "AuditDetails [auditId=" + auditId + ", eventName=" + eventName + ", buId=" + buId + ", subBuId="
				+ subBuId + ", applicationId=" + applicationId + ", environment=" + environment + ", moduleName="
				+ moduleName + ", entityName=" + entityName + ", entityActionName=" + entityActionName + ", actionBy="
				+ actionBy + ", auditDateTime=" + auditDateTime + "]";
	}
	public String getIssuer() {
		return issuer;
	}

	
	public void setissuer(String issuer) {
		this.issuer = issuer;
		
	}

}
