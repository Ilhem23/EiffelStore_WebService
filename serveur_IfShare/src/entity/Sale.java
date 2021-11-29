package entity;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import shared.ISale;

public class Sale extends UnicastRemoteObject implements ISale{
	private int id;
	private int productId;
	private int employeeId;
	private Date saleDate;
	private boolean state;
	
	public Sale(int id, int productId, int employeeId) throws RemoteException {
		super();
		this.id = id;
		this.productId = productId;
		this.employeeId = employeeId;
		this.saleDate= new Date();
		this.state= true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public Date getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	

}
