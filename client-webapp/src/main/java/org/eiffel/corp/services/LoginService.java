package org.eiffel.corp.services;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import entity.BuyService;
import entity.BuyServiceServiceLocator;
import entity.BuyServiceSoapBindingStub;
import entity.Customer;

public class LoginService {

	public static Customer validate(String user, String pwd){
		
		try {
			BuyService buyService = new BuyServiceServiceLocator().getBuyService();
			((BuyServiceSoapBindingStub) buyService).setMaintainSession(true);
	    	Customer cr= buyService.loginCustomer(user, pwd);
	    	if(cr != null) {
	    		return cr;
	    	}
			
		} catch (RemoteException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    	
	}

}
