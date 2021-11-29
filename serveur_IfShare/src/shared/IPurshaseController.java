package shared;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IPurshaseController extends Remote {

	public IPurshase addPurshase(int employeeId, int saleId) throws RemoteException, MalformedURLException, NotBoundException;

	public boolean removePurshase(int id) throws RemoteException;

	public List<IPurshase> getPurshaseByProduct(String productName) throws RemoteException;

	public List<IPurshase> getPurshaseByEmployee(int employeeId) throws RemoteException;
	
	public List<IPurshase> getPurshaseById(int id) throws RemoteException;
	
	public void setPurshases(List<IPurshase> purshases) throws RemoteException; 
	
	public List<IPurshase> getPurshases() throws RemoteException;

	public void updatePurshaseAfterRemovingEmployee(int id) throws RemoteException;
	
	public void updatePurshaseAfterRemovingProduct(int product) throws RemoteException;

	public boolean addPurshaseCustomer(int customerId, List<Integer> saleId) throws RemoteException, MalformedURLException, NotBoundException;

}
