
package com.nc.edu.java.contract.forms;
/*
 * This class is an extends of the Contract class.
 * This class contains new fields such as minutes, sms, gb*/

import javax.xml.bind.annotation.XmlAttribute;

public class MobileContract extends Contract{
	public MobileContract(int id, String dateInput, String dateOut, String numberContract, Person client, int minutes, int sms,
			double gb) {
		super(id, dateInput, dateOut, numberContract, client);
		this.minutes = minutes;
		this.sms = sms;
		this.gb = gb;
	}
	
    //This field is count of minutes for a telephone conversation)
	int minutes;
	
	//This field is number of sms to send messages
	int sms;
	
	//This field is traffic of Internet
	double gb;
	
	public Integer getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public Integer getSms() {
		return sms;
	}
	public void setSms(int sms) {
		this.sms = sms;
	}
	public Double getGb() {
		return gb;
	}
	public void setGb(double gb) {
		this.gb = gb;
	}
	
	//This method returns all fields of our class as an array of objects
	@Override
	public Object[] getAllFields() {
		Object [] allFields = new Object[8];
		allFields[0] = id;
		allFields[1] = dateInput;
		allFields[2] = dateOut;
		allFields[3] = numberContract;
		allFields[4] = client;
		allFields[5] = minutes;
		allFields[6] = sms;
		allFields[7] = gb;
		return allFields;
	}

}
