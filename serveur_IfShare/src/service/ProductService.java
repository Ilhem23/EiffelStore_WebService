package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Product;
import shared.IProduct;
import shared.IProductController;
import shared.IProductObserved;
import shared.IPurshaseController;
import shared.ISale;
import shared.ISaleController;

public class ProductService extends UnicastRemoteObject implements IProductController {
	List<IProduct> products;
	Map<Integer,IProduct> productList;
	ISaleController sc;
	IPurshaseController pc;
	private int idCounter= 0;

	protected ProductService() throws RemoteException {
		super();
		products = new ArrayList<IProduct>();
		productList= new HashMap<Integer, IProduct>();
	}

	private static ProductService single_instance = null;

	public static ProductService GetInstance() throws RemoteException {
		if (single_instance == null) {
			single_instance = new ProductService();
		}
		return single_instance;
	}

	@Override
	public int addProduct(String name, String category, boolean availability, String image, int price, String state) throws RemoteException {
		IProduct product= new Product(getIdCounter(), name, category, availability, image, price, state);
		productList.put(product.getId(), product);
		 return product.getId();	    
			
	}

	@Override
	public List<IProduct> searchByName(String name) throws RemoteException {
		return null;
	}

	@Override
	public IProduct searchById(int id) throws RemoteException {
		return productList.get(id);
	}

	@Override
	public List<IProduct> searchByType(String type) throws RemoteException {
	
		return null;

	}

	@Override
	public List<IProduct> searchByCategory(String category) throws RemoteException {
	
       
		
		return null;

	}

	@Override
	public boolean removeProductById(int id) throws RemoteException {

		return false;

	}

	@Override
	public Map<Integer, IProduct> getproducts() throws RemoteException {
		return productList;
	}

	@Override
	public void setProducts(List<IProduct> products) throws RemoteException {
		this.products = products;
	}

	@Override
	public List<IProduct> getAllProductSoldAtLeastOneTime() throws RemoteException {

		try {

			sc = SaleService.GetInstance();

			List<IProduct> soldProduct = new ArrayList<IProduct>();

			for (ISale p : sc.getSales()) {
				soldProduct.add(this.searchById(p.getProductId()));
			}
			return soldProduct;

		} catch (Exception e) {
			System.out.println("Error in search of products sold at least on time : " + e);
			e.printStackTrace();
		}

		return null;
	}

	public int getIdCounter() {
		idCounter++;
		return idCounter;
	}

	@Override
	public IProductObserved searchByIdObserved(int id) throws RemoteException {
		return (IProductObserved) productList.get(id);
	}

}
