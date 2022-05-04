package com.nc.edu.java.contract.forms;

import javax.xml.bind.annotation.XmlAttribute;

/*
 * This class is an extends of the Contract class.
 * This class contains new field such as speed*/

public class InternetContract extends Contract{
	public InternetContract(int id, String dateInput, String dateOut, String numberContract, Person client, int speed) {
		super(id, dateInput, dateOut, numberContract, client);
		this.speed = speed;
	}
	
    //This field is Internet speed in Mbps
	int speed;
	
	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	//this method returns all fields of our class as an array of objects
	@Override
	public Object[] getAllFields() {
		Object [] allFields = new Object[6];
		allFields[0] = id;
		allFields[1] = dateInput;
		allFields[2] = dateOut;
		allFields[3] = numberContract;
		allFields[4] = client;
		allFields[5] = speed;
		return allFields;
	}

	


}