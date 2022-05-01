package com.nc.edu.java.contract.forms;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


/*
 * This class is an entity for owner contract.
 * This class contains same field such as id, fullName, dateBirth gender, passport and age*/

public class Person {
	public Person(int id, String fullName, String dateBirth, String gender, String passport) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.dateBirth = dateBirth;
		this.gender = gender;
		this.passport = passport;
		this.age = ageOfPerson();
	}
	
    //This field is id of client
	int id;
	
    //This field is full name of client
	String fullName;
	
    //This field is the client's date of birth.
	String dateBirth;
	
	//This field is the client's gender.
	String gender;
	
	//This field is the client's passport data.
	String passport;
	
	//This field is the client's age in years.
	int age;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getDateBirth() {
		return dateBirth;
	}


	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getPassport() {
		return passport;
	}


	public void setPassport(String passport) {
		this.passport = passport;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	/*This method to calculate the age of our client in years.
	 * For calculate the age we are use field dateBirth and today's date*/
	private int ageOfPerson()
	{
		int date = 0;
		String dateNow = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		 try {

	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            Date dateOfBirth = dateFormat.parse(dateBirth);
	            Date dateOfNow = dateFormat.parse(dateNow);	 
	            long milliseconds = (dateOfNow.getTime() - dateOfBirth.getTime())/(1000*60*60*24);
	            date = (int)milliseconds/365;
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		 return date;

	}
	
	public void saveToDB(Connection cn) throws SQLException {
        PreparedStatement preparedStatement = cn.prepareStatement("insert into persons (id, full_name, birth_date, gender, passport) values (?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, this.getId());
        preparedStatement.setString(2, this.getFullName());
        try {
        	//SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        	java.sql.Date dateOfBirth = java.sql.Date.valueOf(dateBirth);
        	preparedStatement.setDate(3, dateOfBirth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        preparedStatement.setString(4, this.getGender());
        preparedStatement.setString(5, this.getPassport());
        preparedStatement.executeUpdate();
    }

    public static Person getFromDBById(Connection cn, int personId) throws SQLException {
        PreparedStatement preparedStatement = cn.prepareStatement("select * from persons where id = ?");
        preparedStatement.setInt(1, personId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Person person = null;
        while (resultSet.next()){
            person = new Person(resultSet.getInt("id"), resultSet.getString("full_name"), new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("birth_date")), resultSet.getString("gender"), resultSet.getString("passport"));
        }
        return person;
    }

}

