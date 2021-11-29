package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IPurshase extends Remote {

	public int getId() throws RemoteException;

	public void setId(int id) throws RemoteException;

	public int getIdSale() throws RemoteException;

	public void setIdSale(int idSale) throws RemoteException;

	public int getEmployeeId() throws RemoteException;

	public void setEmployeeId(int employeeId) throws RemoteException;

	public Date getPurshaseDate() throws RemoteException;

	public void setPurshaseDate(Date purshaseDate) throws RemoteException;

	public boolean isState() throws RemoteException;

	public void setState(boolean state) throws RemoteException;

	public int getCustomerId() throws RemoteException;

	public void setCustomerId(int customerId) throws RemoteException;

}