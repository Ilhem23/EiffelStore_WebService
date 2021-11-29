package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface ISale extends Remote{

	public int getId() throws RemoteException;

	public void setId(int id) throws RemoteException;

	public int getProductId() throws RemoteException;

	public void setProductId(int productId) throws RemoteException;

	public int getEmployeeId() throws RemoteException;

	public void setEmployeeId(int employeeId) throws RemoteException;

	public Date getSaleDate() throws RemoteException;

	public void setSaleDate(Date saleDate) throws RemoteException;
	
	public boolean isState() throws RemoteException;
	
	public void setState(boolean state) throws RemoteException;


}