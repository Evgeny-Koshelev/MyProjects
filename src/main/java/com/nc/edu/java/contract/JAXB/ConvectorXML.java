package com.nc.edu.java.contract.JAXB;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.nc.edu.java.contract.forms.Contract;
import com.nc.edu.java.contract.forms.InternetContract;
import com.nc.edu.java.contract.forms.MobileContract;
import com.nc.edu.java.contract.forms.TvContract;

public class ConvectorXML extends XmlAdapter <ContractXML, Contract> {
    @Override
    public Contract unmarshal(ContractXML contractXML) throws Exception {
        if (null == contractXML) {
            return null;
        }
        if (contractXML.pakegeOfCanals != null) {
            TvContract tvContract = new TvContract(
            		contractXML.id,
            		contractXML.dateInput,
            		contractXML.dateOut,
            		contractXML.numberContract,
            		contractXML.client,
                    contractXML.pakegeOfCanals);
            return tvContract;
        } else if ((contractXML.sms != 0) ||contractXML.minutes != 0 || contractXML.gb != 0) {
            MobileContract mobileContract = new MobileContract(
            		contractXML.id,
            		contractXML.dateInput,
            		contractXML.dateOut,
            		contractXML.numberContract,
            		contractXML.client,
            		contractXML.sms,
            		contractXML.minutes,
            		contractXML.gb);
            return mobileContract;
        } else if (contractXML.speed != 0) {
            InternetContract internetContract = new InternetContract(
            		contractXML.id,
            		contractXML.dateInput,
            		contractXML.dateOut,
            		contractXML.numberContract,
            		contractXML.client,
            		contractXML.speed);
            return internetContract;
        }
        else{
            return null;
        }
    }

    @Override
    public ContractXML marshal(Contract contract) throws Exception {
        if (contract == null) {
            return null;
        }
        ContractXML contractXML = new ContractXML();
        if (contract instanceof TvContract) {
        	TvContract tvContract = (TvContract) contract;
        	contractXML.id = tvContract.getId();
        	contractXML.dateInput = tvContract.getDateInput();
        	contractXML.dateOut = tvContract.getDateOut();
        	contractXML.numberContract = tvContract.getNumberContract();
        	contractXML.client = tvContract.getClient();
        	contractXML.pakegeOfCanals = tvContract.getPakegeofcanals();
        } else if (contract instanceof MobileContract) {
            MobileContract mobileContract = (MobileContract) contract;
            contractXML.id = mobileContract.getId();
        	contractXML.dateInput = mobileContract.getDateInput();
        	contractXML.dateOut = mobileContract.getDateOut();
        	contractXML.numberContract = mobileContract.getNumberContract();
        	contractXML.client = mobileContract.getClient();
        	contractXML.sms = mobileContract.getSms();
        	contractXML.minutes = mobileContract.getMinutes();
        	contractXML.gb = mobileContract.getGb();
        } else if (contract instanceof InternetContract) {
            InternetContract internetContract = (InternetContract) contract;
            contractXML.id = internetContract.getId();
        	contractXML.dateInput = internetContract.getDateInput();
        	contractXML.dateOut = internetContract.getDateOut();
        	contractXML.numberContract = internetContract.getNumberContract();
        	contractXML.client = internetContract.getClient();
        	contractXML.speed = internetContract.getSpeed();
        }
        return contractXML;
    }

}
