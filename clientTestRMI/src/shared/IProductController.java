package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface IProductController extends Remote {

	public int addProduct(String name, String category, boolean availability, String image, int price, String state) throws RemoteException;

	public List<IProduct> searchByName(String name) throws RemoteException;

	public IProduct searchById(int id) throws RemoteException;
	
	public IProductObserved searchByIdObserved(int id) throws RemoteException;

	public List<IProduct> searchByType(String type) throws RemoteException;

	public List<IProduct> searchByCategory(String category) throws RemoteException;
	
	public boolean removeProductById(int id) throws RemoteException;
	
	public Map<Integer, IProduct> getproducts() throws RemoteException ;
	
	public void setProducts(List<IProduct> products)  throws RemoteException;

	public List<IProduct> getAllProductSoldAtLeastOneTime() throws RemoteException;

}
