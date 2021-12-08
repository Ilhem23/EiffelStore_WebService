package service;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import entity.Sale;
import luncher.IfShareServer;
import shared.IProduct;
import shared.IPurshase;
import shared.ISale;
import shared.ISaleController;

public class SaleService extends UnicastRemoteObject implements ISaleController{

	Map<Integer,ISale> productSale;
	private Set<Integer> soldAtLeastOnes;
	
	public Set<Integer> getSoldAtLeastOnes() throws RemoteException {
		Set<Integer> solds= new HashSet<Integer>();
		ProductService productService= ProductService.GetInstance();
		// Iterator<Integer> itr = soldAtLeastOnes.iterator();
		if(!soldAtLeastOnes.isEmpty())
		{
		for(int sale : soldAtLeastOnes)
		{
			 
				 solds.add(sale);
			 		
		}
		}
		
		return solds;
	}

	public void setSoldAtLeastOnes(Set<Integer> soldAtLeastOnes) {
		this.soldAtLeastOnes = soldAtLeastOnes;
	}
	private int idCounter= 0;

	protected SaleService() throws RemoteException {
		super();
		productSale = new HashMap<Integer, ISale>();
		soldAtLeastOnes = new HashSet<Integer>();
		
	}
	private static SaleService single_instance = null;

	public static SaleService GetInstance() throws RemoteException {
		// To ensure only one instance is created
		if (single_instance == null) {
			single_instance = new SaleService();
		}
		return single_instance;
	}


	@Override
	public ISale addSale(int productId, int employeeId) throws RemoteException {
		ISale sale= new Sale(getIdCounter(), productId, employeeId);
		productSale.put(sale.getId(),sale);
		return sale;
	}
	
	@Override
	public ISale reSale(int idPurshase, int price, String state) throws RemoteException, MalformedURLException, NotBoundException {
		PurshaseService purshaseServices= PurshaseService.GetInstance();
		ProductService productService= ProductService.GetInstance();
		IPurshase purshase= purshaseServices.purshases.get(idPurshase);
		int saleId= purshase.getIdSale();
		ISale sale= productSale.get(saleId);
		IProduct product= productService.searchById(sale.getProductId());
		product.setAvailability(true);
		product.setPrice(price);
		product.setState(state);
		purshase.setState(false);
		ISale resale= addSale(product.getId(), purshase.getEmployeeId());
		soldAtLeastOnes.add(product.getId());
		return resale;
	}

	@Override
	public boolean removeSale(int id) throws RemoteException {
		productSale.remove(id);
		return true;
	}

	@Override
	public List<ISale> getSalesByProduct(int idProduct) throws RemoteException {
		
		return null;
	}

	@Override
	public List<ISale> getSalesByEmployee(int employeeId) throws RemoteException {
		List<ISale> sales= new ArrayList<>();
		for(ISale p: productSale.values()) {
			if(p != null) {
				if(p.getEmployeeId() == employeeId) {
					sales.add(p);
				}
			}
			
		}
		return sales;
	}

	@Override
	public ISale getSaleById(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return productSale.get(id);
	}

	@Override
	public void setSales(List<ISale> sales) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ISale> getSales() throws RemoteException {

		List<ISale> listSales = productSale.values().stream().collect(Collectors.toList());
	    return listSales;
	}

	@Override
	public void updateSaleeAfterRemovingEmployee(int id) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSaleAfterRemovingProduct(int product) throws RemoteException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public ISale getCurrentSaleByProduct(int idProduct) throws RemoteException {
		ProductService productService= ProductService.GetInstance();
		for (Map.Entry<Integer,ISale> set :
            productSale.entrySet()) {
			int id= set.getValue().getProductId();
            
           if(id == idProduct)
           {
        		   if(set.getValue().isState()) {
        			   return set.getValue();  
        		   
        	   }
        	   
           }
          
       }
		return null;
	}

	public int getIdCounter() {
		idCounter++;
		return idCounter;
	}



}
