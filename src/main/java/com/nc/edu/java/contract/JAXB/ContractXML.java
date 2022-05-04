package com.nc.edu.java.contract.JAXB;

import javax.xml.bind.annotation.*;

import com.nc.edu.java.contract.forms.Person;

@XmlAccessorType(XmlAccessType.FIELD)
public class ContractXML{
	
	 //This field is id of contact
		@XmlAttribute(name = "idContact")
		int id;
		
	    //This field is start date of contact
		@XmlAttribute(name = "dateInputContact")
		String dateInput;
		
	    //This field is end date of contact
		@XmlAttribute(name = "dateOutContact")
		String dateOut;
		
	    //This field is number of contact
		@XmlAttribute(name = "number")
		String numberContract;
		
	    //This field is contract owner
		@XmlElement(name = "clientContact")
		Person client;
		
	    //This field is Internet speed in Mbps
		@XmlAttribute(name = "speedInternet")
		int speed;
		
	    //This field is count of minutes for a telephone conversation
		@XmlAttribute(name = "minutesCall")
		int minutes;
		
		//This field is number of sms to send messages
		@XmlAttribute(name = "smsNumber")
		int sms;
		
		//This field is traffic of Internet
		@XmlAttribute(name = "gbAmount")
		double gb;
		
	    //This field is name of the canals package for digital TV
		@XmlAttribute(name = "pakegeCanals")
		String pakegeOfCanals;

}
