package org.eiffel.corp.services;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import entity.BuyService;
import entity.BuyServiceServiceLocator;
import entity.BuyServiceSoapBindingStub;
import entity.Customer;

public class SignupService {
	
	public static int signup(String firstName, String lastName, String email, String login, String password) throws ServiceException, RemoteException {
		BuyService buyService = new BuyServiceServiceLocator().getBuyService();
    	((BuyServiceSoapBindingStub) buyService).setMaintainSession(true);
    	Customer cr= buyService.addCustomer(firstName, lastName, email, login, password);
    	buyService.createBankAccount(cr.getId());
		return cr.getId();
	}

}
