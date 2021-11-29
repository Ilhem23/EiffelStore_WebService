package shared;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

public interface ISaleController extends Remote{
	public ISale addSale(int productId, int employeeId) throws RemoteException;

	public boolean removeSale(int id) throws RemoteException;

	public List<ISale> getSalesByProduct(int idProduct) throws RemoteException;

	public List<ISale> getSalesByEmployee(int employeeId) throws RemoteException;
	
	public ISale getSaleById(int id) throws RemoteException;
	
	public void setSales(List<ISale> sales) throws RemoteException; 
	
	public List<ISale> getSales() throws RemoteException;

	public void updateSaleeAfterRemovingEmployee(int id) throws RemoteException;
	
	public void updateSaleAfterRemovingProduct(int product) throws RemoteException;

	public ISale reSale(int idPurshase, int price, String state) throws RemoteException, MalformedURLException, NotBoundException;

	public ISale getCurrentSaleByProduct(int idProduct) throws RemoteException;
	
	public Set<Integer> getSoldAtLeastOnes() throws RemoteException;
	
	public void setSoldAtLeastOnes(Set<Integer> soldAtLeastOnes) throws RemoteException;
	
}
