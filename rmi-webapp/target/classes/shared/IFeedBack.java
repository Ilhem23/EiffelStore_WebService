package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IFeedBack extends Remote{

	public int getId() throws RemoteException;

	public void setId(int id) throws RemoteException;;

	public int getProductId() throws RemoteException;

	public void setProductId(int productId) throws RemoteException;

	public int getEmployeeId() throws RemoteException;

	public void setEmployeeId(int employeeId) throws RemoteException;

	public int getRating() throws RemoteException;

	public void setRating(int rating) throws RemoteException;

	public String getComment() throws RemoteException;

	public void setComment(String comment) throws RemoteException;

	public Date getDateComment() throws RemoteException;

	public void setDateComment(Date dateComment) throws RemoteException;

}