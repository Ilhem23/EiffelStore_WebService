package service;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import entity.Purshase;
import entity.Sale;
import luncher.IfShareServer;
import shared.IEmployee;
import shared.IEmployeeController;
import shared.IFeedBack;
import shared.IProduct;
import shared.IPurshase;
import shared.IPurshaseController;
import shared.ISale;

public class PurshaseService extends UnicastRemoteObject implements IPurshaseController{
	Map<Integer,IPurshase> purshases;
	private int idCounter= 0;
	Map<Integer, LinkedList<IEmployee>> waitingList;

	IEmployeeController emp;
	protected PurshaseService() throws RemoteException {
		super();
		purshases= new HashMap<Integer, IPurshase>();
		waitingList= new HashMap<Integer, LinkedList<IEmployee>>();
	}
	
	private static PurshaseService single_instance = null;

	public static PurshaseService GetInstance() throws RemoteException {
		// To ensure only one instance is created
		if (single_instance == null) {
			single_instance = new PurshaseService();
		}
		return single_instance;
	}


	@Override
	public IPurshase addPurshase(int employeeId, int saleId) throws RemoteException, MalformedURLException, NotBoundException {

		ProductService productService= ProductService.GetInstance();
		SaleService saleService = SaleService.GetInstance();
		int productId= saleService.getSaleById(saleId).getProductId();
		IProduct product= productService.searchById(productId);
		if(product.isAvailability()) {
			IPurshase purshase= new Purshase(getIdCounter(), employeeId, saleId);
			productService.searchById(productId).setAvailability(false);
			saleService.getSaleById(saleId).setState(false);
			purshases.put(purshase.getId(), purshase);
			return purshase;	
		}else {
			emp= IfShareServer.GetInstance();
			if(waitingList.containsKey(product.getId()))
			{
				
				LinkedList<IEmployee> empList = waitingList.get(product.getId());
				IEmployee emp1= emp.searchEmployeeById(employeeId);
				if(!empList.contains(emp1))
				{
					empList.addLast(emp1);
					System.out.println("you have already request this product");
				}	
				
			}else {
				LinkedList<IEmployee> empList= new LinkedList<IEmployee>();
				IEmployee emp1= emp.searchEmployeeById(employeeId);
				empList.addLast(emp1);
				waitingList.put(product.getId(), empList);	
			}
			return null;			
		}	
		
	}

	@Override
	public boolean removePurshase(int id) throws RemoteException {
		purshases.remove(id);
		return true;
	}

	@Override
	public List<IPurshase> getPurshaseByProduct(String productName) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IPurshase> getPurshaseByEmployee(int employeeId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IPurshase> getPurshaseById(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPurshases(List<IPurshase> purshases) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IPurshase> getPurshases() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePurshaseAfterRemovingEmployee(int id) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePurshaseAfterRemovingProduct(int product) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	public Map<Integer, LinkedList<IEmployee>> getWaitingList() {
		return waitingList;
	}



	public int getIdCounter() {
		idCounter++;
		return idCounter;
	}


	@Override
	public boolean addPurshaseCustomer(int customerId, List<Integer> saleId)
			throws RemoteException, MalformedURLException, NotBoundException {
		List<IProduct> products= new ArrayList<IProduct>();
		ProductService productService= ProductService.GetInstance();
		SaleService saleService = SaleService.GetInstance();
		for(int i: saleId)
		{
			int id= saleService.getSaleById(i).getProductId();
			products.add(productService.searchById(id));
			IPurshase purshase= new Purshase();
			purshase.setCustomerId(customerId);
			purshase.setId(getIdCounter());
			purshase.setIdSale(i);
			saleService.getSaleById(i).setState(false);
			purshases.put(purshase.getId(), purshase);
			
		}
		return true;
		
	}

}
