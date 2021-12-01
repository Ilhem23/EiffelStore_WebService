package entity;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import service.EmployeeService;
import shared.Employee;
import shared.IRequestObserver;

public class Request  extends UnicastRemoteObject implements IRequestObserver{
	
	private int idProduct;
	private int employeeId;
	
	public Request(int idProduct, int employeeId) throws RemoteException {
		super();
		this.idProduct= idProduct;
		this.employeeId= employeeId;
	}

	public int getIdProduct() {
		return idProduct;
	}


	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public void notifyChange() throws RemoteException {
		Employee emp;
		EmployeeService empService= EmployeeService.GetInstance();
		emp= empService.searchEmployeeById(employeeId);
		emp.addToInbox(idProduct);
	}




}
