package com.vgavva.notme;

import java.math.BigInteger;

public class NotficationStats {
	private int totalNotfns;
	private int emailNotfns;
	private int smsNotfns;
	private int pendingEmailNotfns;
	private int pendingSmsNotfns;
	private int pendingTotalNotfns;
	private int port;
	
	public NotficationStats() {
		
	}

	public NotficationStats(int totalNotfns, int emailNotfns, int smsNotfns, int pendingEmailNotfns,
			int pendingSmsNotfns, int pendingTotalNotfns, int port) {
		super();
		this.totalNotfns = totalNotfns;
		this.emailNotfns = emailNotfns;
		this.smsNotfns = smsNotfns;
		this.pendingEmailNotfns = pendingEmailNotfns;
		this.pendingSmsNotfns = pendingSmsNotfns;
		this.pendingTotalNotfns = pendingTotalNotfns;
		this.port = port;
	}

	public int getTotalNotfns() {
		return totalNotfns;
	}

	public void setTotalNotfns(int totalNotfns) {
		this.totalNotfns = totalNotfns;
	}

	public int getEmailNotfns() {
		return emailNotfns;
	}

	public void setEmailNotfns(int emailNotfns) {
		this.emailNotfns = emailNotfns;
	}

	public int getSmsNotfns() {
		return smsNotfns;
	}

	public void setSmsNotfns(int smsNotfns) {
		this.smsNotfns = smsNotfns;
	}

	public int getPendingEmailNotfns() {
		return pendingEmailNotfns;
	}

	public void setPendingEmailNotfns(int pendingEmailNotfns) {
		this.pendingEmailNotfns = pendingEmailNotfns;
	}

	public int getPendingSmsNotfns() {
		return pendingSmsNotfns;
	}

	public void setPendingSmsNotfns(int pendingSmsNotfns) {
		this.pendingSmsNotfns = pendingSmsNotfns;
	}

	public int getPendingTotalNotfns() {
		return pendingTotalNotfns;
	}

	public void setPendingTotalNotfns(int pendingTotalNotfns) {
		this.pendingTotalNotfns = pendingTotalNotfns;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	
	
	
	
}
