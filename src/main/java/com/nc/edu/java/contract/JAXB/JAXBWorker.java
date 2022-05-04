package com.nc.edu.java.contract.JAXB;

import java.io.File;

import javax.xml.bind.*;

import com.nc.edu.java.contract.repositoriy.ContractsRepositoriy;

public class JAXBWorker {
	
	 public static void saveToXml(ContractsRepositoriy contracts, String filePath) {
	        try {
	        	JAXBContext context = JAXBContext.newInstance(ContractsRepositoriy.class);
	            Marshaller marshaller = context.createMarshaller();
	            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	            marshaller.marshal(contracts, new File(filePath));
	        } catch (JAXBException e){
	            e.printStackTrace();
	        }
	    }

	    public static Object getFromXml(String filePath, Class<?> clazz) {
	        try {
	            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
	            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	            return unmarshaller.unmarshal(new File(filePath));
	        } catch (JAXBException e){
	            e.printStackTrace();
	        }
	        return null;
	    }

}
