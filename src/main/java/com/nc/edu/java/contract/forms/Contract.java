package com.nc.edu.java.contract.forms;

/*
 * It is abstract class is base class for all contract types.
 * This class contains fields common to all types of contracts
 * such as id, dateInput, dateOut, numberContract, client*/

public abstract class Contract {
	public Contract(int id, String dateInput, String dateOut, String numberContract, Person client) {
		super();
		this.id = id;
		this.dateInput = dateInput;
		this.dateOut = dateOut;
		this.numberContract = numberContract;
		this.client = client;
	}
    //This field is id of contact
	int id;
	
    //This field is start date of contact
	String dateInput;
	
    //This field is end date of contact
	String dateOut;
	
    //This field is number of contact
	String numberContract;
	
    //This field is contract owner
	Person client;
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDateInput() {
		return dateInput;
	}
	public void setDateInput(String dateInput) {
		this.dateInput = dateInput;
	}
	public String getDateOut() {
		return dateOut;
	}
	public void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}
	public String getNumberContract() {
		return numberContract;
	}
	public void setNumberContract(String numberContract) {
		this.numberContract = numberContract;
	}
	public Person getClient() {
		return client;
	}
	public void setClient(Person client) {
		this.client = client;
	}
	
	//this method returns all fields of our class 
	public abstract Object[] getAllFields(); 


}
