package com.kd.tv.model;

public class TrustRequestModel {
	private String TPid;

	private String participant_uid;

	private String transfer_uid;

	private boolean status;

	private String status_code;

	public String getTPid() {
		return TPid;
	}

	public void setTPid(String tPid) {
		TPid = tPid;
	}

	public String getParticipant_uid() {
		return participant_uid;
	}

	public void setParticipant_uid(String participant_uid) {
		this.participant_uid = participant_uid;
	}

	public String getTransfer_uid() {
		return transfer_uid;
	}

	public void setTransfer_uid(String transfer_uid) {
		this.transfer_uid = transfer_uid;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}
}
