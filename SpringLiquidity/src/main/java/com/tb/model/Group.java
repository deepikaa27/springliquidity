package com.tb.model;
public class Group
{
	private int groupId;
	private String groupName;
	private String rateId;
	private int accountNo;
	public Group(int groupId, String groupName, String rateId, int accountNo) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.rateId = rateId;
		this.accountNo = accountNo;
	}
	public Group() {
		super();
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getRateId() {
		return rateId;
	}
	public void setRateId(String rateId) {
		this.rateId = rateId;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	@Override
	public String toString() {
		return "Group [groupId=" + groupId + ", groupName=" + groupName + ", rateId=" + rateId + ", accountNo="
				+ accountNo + "]";
	}
	
}