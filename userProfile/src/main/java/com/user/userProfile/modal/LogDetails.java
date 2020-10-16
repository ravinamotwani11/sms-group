package com.user.userProfile.modal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogDetails implements Cloneable {
	@JsonProperty
	private String interfaceName;
	@JsonProperty
	private String classname;
	@JsonProperty
	private String functionName;
	@JsonProperty
	private String logType;
	@JsonProperty
	private String logDateTime;
	@JsonProperty
	private String logData;
	@JsonProperty
	private String applicationID;
	@JsonProperty
	private String logBasePath;
	@JsonProperty
	private String logFormat;
	@JsonProperty
	private String environment;
	@JsonProperty
	private String logModule;
	@JsonProperty
	private String MasterId;
	@JsonProperty
	private Integer buId;
	@JsonProperty
	private Integer subBuId;
	@JsonProperty
	private String userlogin;
	@JsonProperty
	private String issuer;
	
	

	

	public String getUserlogin() {
		return userlogin;
	}

	public void setUserlogin(String userlogin) {
		this.userlogin = userlogin;
	}

	public LogDetails() {
		super();
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getLogDateTime() {
		return logDateTime;
	}

	public void setLogDateTime(String logDateTime) {
		this.logDateTime = logDateTime;
	}

	public String getLogData() {
		return logData;
	}

	public void setLogData(String logData) {
		this.logData = logData;
	}

	public String getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}

	public String getLogBasePath() {
		return logBasePath;
	}

	public void setLogBasePath(String logBasePath) {
		this.logBasePath = logBasePath;
	}

	public String getLogFormat() {
		return logFormat;
	}

	public void setLogFormat(String logFormat) {
		this.logFormat = logFormat;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getLogModule() {
		return logModule;
	}

	public void setLogModule(String logModule) {
		this.logModule = logModule;
	}

	public String getMasterId() {
		return MasterId;
	}

	public void setMasterId(String masterId) {
		MasterId = masterId;
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



	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	@Override
	public String toString() {
		return "LogDetails [interfaceName=" + interfaceName + ", classname=" + classname + ", functionName="
				+ functionName + ", logType=" + logType + ", logDateTime=" + logDateTime + ", logData=" + logData
				+ ", applicationID=" + applicationID + ", logBasePath=" + logBasePath + ", logFormat=" + logFormat
				+ ", environment=" + environment + ", logModule=" + logModule + ", MasterId=" + MasterId + ", buId="
				+ buId + ", subBuId=" + subBuId + "]";
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}


	
}
