package com.nc.edu.java.contract.files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.nc.edu.java.contract.forms.Contract;
import com.nc.edu.java.contract.forms.InternetContract;
import com.nc.edu.java.contract.forms.MobileContract;
import com.nc.edu.java.contract.forms.Person;
import com.nc.edu.java.contract.forms.TvContract;
import com.nc.edu.java.contract.repositoriy.ContractsRepositoriy;

public class ReadFile {

	public void read(String path, ContractsRepositoriy contracts)
	{
		BufferedReader reader = null;
		String line = "";
		
		try {
			reader = new BufferedReader(new FileReader(path));
			
			while((line = reader.readLine()) != null)
			{
				String [] row = line.split(";|\\/" );
				int personId = 0;
				try {
					personId =Integer.valueOf(row[0]);
				}
				catch(NumberFormatException e) {
					e.printStackTrace();
				}
				Person person = new Person (personId,row[1],row[2],row[3],row[4]);
				Contract [] contract = new Contract[1];
				int id = contracts.getCloneContractsArray().length+1;
				if(row[8].equals("internet"))
				{
					int speedInternet = 0;
					try {
						speedInternet =Integer.valueOf(row[9]);
					}
					catch(NumberFormatException e) {
						e.printStackTrace();
					}
					contract[0] = new InternetContract(id,row[5],row[6],row[7],person,speedInternet);
				}
				else if(row[8].equals("mobile"))
				{
					int minutes = 0, sms = 0;
					double gb = 0;
					try {
						minutes =Integer.valueOf(row[9]);
						sms =Integer.valueOf(row[10]);
						gb =Double.valueOf(row[11]);
					}
					catch(NumberFormatException e) {
						e.printStackTrace();
					}
					contract[0] = new MobileContract(id,row[5],row[6],row[7],person,
							minutes,sms,gb);
					
				}
				else
				{
					contract[0] = new TvContract(id,row[5],row[6],row[7],person,row[9]);
					
				}
				contracts.addContract(contract);
			}
		}
		catch(Exception e) {
			e.printStackTrace();

		}
		finally
		{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
