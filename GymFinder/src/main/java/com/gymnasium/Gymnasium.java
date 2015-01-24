package com.gymnasium;

public class Gymnasium  {

	private String name;
	private String contact;
	private String address;	
	private String website;
	private String hoursOfOperation;
	
	public Gymnasium() {
		// TODO Auto-generated constructor stub
	}
	
	public Gymnasium(String name, String contact, String address, String website, String hoursOfOperation) {
		this.name = name;
		this.contact = contact;
		this.address = address;
		this.website = website;
		this.hoursOfOperation = hoursOfOperation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String getHoursOfOperation() {
		return hoursOfOperation;
	}

	public void setHoursOfOperation(String hoursOfOperation) {
		this.hoursOfOperation = hoursOfOperation;
	}
	
	@Override
	public String toString() {
		return "Gymnasium [name=" + name + ", contact=" + contact + ", address=" + address
				+ ", website=" + website + ", hours of operation=" + hoursOfOperation + "]";
	}
}
