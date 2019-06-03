package com.kd.tv.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sensitive_information")
public class SensitiveInfoEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer sid;
	
	private String transfer_uid;
	
	private String info_key;
	
	private String info_value;
	
	private Date created_date;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getTransfer_uid() {
		return transfer_uid;
	}

	public void setTransfer_uid(String transfer_uid) {
		this.transfer_uid = transfer_uid;
	}

	public String getInfo_key() {
		return info_key;
	}

	public void setInfo_key(String info_key) {
		this.info_key = info_key;
	}

	public String getInfo_value() {
		return info_value;
	}

	public void setInfo_value(String info_value) {
		this.info_value = info_value;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

}
