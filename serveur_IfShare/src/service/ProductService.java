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
		init();
	}

	private static ProductService single_instance = null;

	public static ProductService GetInstance() throws RemoteException {
		if (single_instance == null) {
			single_instance = new ProductService();
			
		}
		return single_instance;
	}
	public void init() throws RemoteException {
		
		addProduct("Bamboo Watch",  "Accessories", "Product Description", true,"bamboo-watch.jpg", 65, "new");
		addProduct("Black Watch",  "Accessories", "Product Description", true,"black-watch.jpg", 72, "used - like new");
		addProduct("Blue Band",  "Fitness", "Product Description", true,"blue-band.jpg", 79, "new");
		addProduct("Blue T-Shirt",  "Clothing", "Product Description", true,"blue-t-shirt.jpg", 29, "used - like new");
		addProduct("Bracelet",  "Accessories", "Product Description", true,"bracelet.jpg", 15, "new");
		addProduct("Brown Purse",  "Accessories", "Product Description", true,"brown-purse.jpg", 120, "new");
		addProduct("Chakra Bracelet",  "Accessories", "Product Description", true,"chakra-bracelet.jpg", 32, "new");
		addProduct("Galaxy Earrings",  "Accessories", "Product Description", true,"galaxy-earrings.jpg", 34, "new");
		addProduct("Game Controller",  "Electronics", "Product Description", true,"game-controller.jpg", 99, "new");
		addProduct("Gaming Set",  "Electronics", "Product Description", true,"gaming-set.jpg", 150, "Used - good condition");
		addProduct("Gold Phone Case",  "Accessories", "Product Description", true,"gold-phone-case.jpg", 24, "new");
		addProduct("Green Earbuds",  "Electronics", "Product Description", true,"green-earbuds.jpg", 20, "Used - fairly good condition");
		addProduct("Green T-Shirt",  "Clothing", "Product Description", true,"green-t-shirt.jpg", 49, "new");
		addProduct("Grey T-Shirt",  "Clothing", "Product Description", true,"grey-t-shirt.jpg", 48, "new");
		addProduct("Gaming Set",  "Electronics", "Product Description", true,"gaming-set.jpg", 150, "Used - good condition");
		addProduct("Sneakers",  "Clothing", "Product Description", true,"sneakers.jpg", 50, "used - like new");
		addProduct("Shoes",  "Clothing", "Product Description", true,"shoes.jpg", 64, "new");
	}
	
	
	

	@Override
	public int addProduct(String name, String category, String description,  boolean availability, String image, int price, String state) throws RemoteException {
		IProduct product= new Product(getIdCounter(), name, category, availability, image, price, state);
		product.setDescription(description);
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
