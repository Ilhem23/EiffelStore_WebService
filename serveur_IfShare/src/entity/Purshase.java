package entity;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import shared.IPurshase;

public class Purshase extends UnicastRemoteObject implements IPurshase{
	private int id;
	private int idSale;
	private int employeeId;
	private int customerId;
	private Date purshaseDate;
	private boolean state;
	
	public Purshase(int id, int employeeId, int idSale) throws RemoteException {
		super();
		this.id = id;
		this.setIdSale(idSale);
		this.employeeId = employeeId;
		this.purshaseDate= new Date();
		this.state= true;
	}
	public Purshase() throws RemoteException {
		super();
		this.purshaseDate= new Date();
		this.state= true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public Date getPurshaseDate() {
		return purshaseDate;
	}
	public void setPurshaseDate(Date purshaseDate) {
		this.purshaseDate = purshaseDate;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public int getIdSale() {
		return idSale;
	}
	public void setIdSale(int idSale) {
		this.idSale = idSale;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	

}
