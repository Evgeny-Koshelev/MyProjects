package com.nc.edu.java.contract.forms;
/*
 * This class is an extends of the Contract class.
 * This class contains new field such as pakgeOfCanals*/

import javax.xml.bind.annotation.XmlAttribute;

public class TvContract extends Contract {
	public TvContract(int id, String dateInput, String dateOut, String numberContract, Person client, String pakegeOfCanals) {
		super(id, dateInput, dateOut, numberContract, client);
		this.pakegeOfCanals = pakegeOfCanals;
	}

    //This field is name of the canals package for digital TV)
	String pakegeOfCanals;

	public String getPakegeofcanals() {
		return pakegeOfCanals;
	}

	public void setPakegeofcanals(String pakegeOfCanals) {
		this.pakegeOfCanals = pakegeOfCanals;
	}
	
	//This method returns all fields of our class as an array of objects
	@Override
	public Object[] getAllFields() {
		Object [] allFields = new Object[6];
		allFields[0] = id;
		allFields[1] = dateInput;
		allFields[2] = dateOut;
		allFields[3] = numberContract;
		allFields[4] = client;
		allFields[5] = pakegeOfCanals;
		return allFields;
	}

}
