package com.techelevator.model.site;

public class Site {
	private Long siteId;
	private Long campId;
	private Long siteNum;
	private int	maxCap;
	private boolean access;
	private int maxRvLen;
	private boolean util;
	private String cost;
	
	
	
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public Long getSiteId() {
		return siteId;
	}
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
	public Long getCampId() {
		return campId;
	}
	public void setCampId(Long campId) {
		this.campId = campId;
	}
	public Long getSiteNum() {
		return siteNum;
	}
	public void setSiteNum(Long siteNum) {
		this.siteNum = siteNum;
	}
	public int getMaxCap() {
		return maxCap;
	}
	public void setMaxCap(int maxCap) {
		this.maxCap = maxCap;
	}
	public boolean isAccess() {
		return access;
	}
	public void setAccess(boolean access) {
		this.access = access;
	}
	public int getMaxRvLen() {
		return maxRvLen;
	}
	public void setMaxRvLen(int maxRvLen) {
		this.maxRvLen = maxRvLen;
	}
	public boolean isUtil() {
		return util;
	}
	public void setUtil(boolean util) {
		this.util = util;
	}
	
	
		// toString override that displays site information in a readable format for the user
	@Override	public String toString() {
		return (siteNum + "\t\t" + maxCap + "\t\t" + yesNo(access) + "\t\t" + maxRvLen + "\t\t" + yesNo(util) + "\t\t$" + cost + "\n");
	}
	
		// method to take a boolean true and return a readable yes
private String yesNo(boolean hold) {	
	if(hold == true) {
		return "Yes";
	}else {
		return "No";
	}
	
	
}
	


}
