package org.eiffel.corp.services;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.eiffel.corp.utils.SessionUtils;

import shared.Employee;
import shared.IEmployeeController;
import shared.IFeedBack;
import shared.IFeedbackController;
import shared.IProduct;
import shared.IProductController;
import shared.IPurshase;
import shared.IPurshaseController;
import shared.ISale;
import shared.ISaleController;

@Named
@ApplicationScoped
public class StoreEmpService {
	private  IProductController productService = null;
	private  ISaleController saleService = null;
	private  IPurshaseController purshaseService = null;
	private  IEmployeeController employeeService = null;
	private  IFeedbackController feedBack = null;

    private List<IProduct> products;

    private  List<IProduct> selectedProducts;
    
    private Map<Integer, List<IFeedBack>> feedBacks;


	@PostConstruct
    public void init() {
    	try {
    		
    		productService = (IProductController) Naming.lookup("rmi://localhost:1099/productService");
    		saleService = (ISaleController) Naming.lookup("rmi://localhost:1099/saleService");
    		purshaseService = (IPurshaseController) Naming.lookup("rmi://localhost:1099/purshaseService");
    		employeeService= (IEmployeeController) Naming.lookup("rmi://localhost:1100/employeeService");
    		feedBack = (IFeedbackController) Naming.lookup("rmi://localhost:1099/feedBack");
    		Map<Integer, IProduct> pro= productService.getproducts();
    		products = new ArrayList<IProduct>(pro.values());    	
        	init_feedBack();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	

    }
    public List<IProduct> getSelectedProducts() {
  		return selectedProducts;
  	}
  
   
  	public void setSelectedProducts(IProduct selectedProducts) {
  		this.selectedProducts.add(selectedProducts);
  	}
  	/*
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }
	*/
    public List<IProduct> getProducts(int size) {
    	init();
    	size= products.size();

        if (size > products.size()) {
            Random rand = new Random();

            List<IProduct> randomList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int randomIndex = rand.nextInt(products.size());
                randomList.add(products.get(randomIndex));
            }

            return randomList;
        }

        else {
        	size= products.size();
            return new ArrayList<>(products.subList(0, size));
        }
    }
    public List<IProduct> getPurchases(){
    	List<IProduct> prod= new ArrayList<>();
    	
    	try {
    		HttpSession session = SessionUtils.getSession();
            int employeeId= (int) session.getAttribute("id"); 
			List<IPurshase> purshase= purshaseService.getPurshaseByEmployee(employeeId);
			for(IPurshase p: purshase) {
				if(p != null) {
					ISale s= saleService.getSaleById(p.getIdSale());
					prod.add(productService.searchById(s.getProductId()));
					
				}
			}
			return prod;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
    }
    
    public void init_feedBack() {
    	try {
			 feedBacks= new HashMap<Integer, List<IFeedBack>>();
		     for(IProduct p: products) {
		      List<IFeedBack> fed= new ArrayList<IFeedBack>();
		      fed= feedBack.searchByProduct(p.getId());
		      if(fed != null) {
	            feedBacks.put(p.getId(), fed);
	            }
	        }
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public List<IFeedBack> getFeedBack(int idProduct){
    	return feedBacks.get(idProduct);
    }
    public String getEmployeeName(int id) {
    	Employee emp;
		try {
			emp = employeeService.searchEmployeeById(id);
			return emp.getFirstName()+" "+ emp.getLastName();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return " ";
    }
	public  IEmployeeController getEmployeeService() {
		return employeeService;
	}
	public void setEmployeeService(IEmployeeController employeeService) {
		employeeService = employeeService;
	}
	public boolean verif(int productId) {
		HttpSession session = SessionUtils.getSession();
        int emp1= (int) session.getAttribute("id");
		int empId;
		try {
			ISale s= saleService.getCurrentSaleByProduct(productId);
			if(s != null) {
				empId = s.getEmployeeId();
				if(emp1 == empId) {
					return false;
				}
			}
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	public String getInventory(int productId) {
		IProduct p;
		try {
			p = productService.searchById(productId);
			if(p.isAvailability()) {
				return "IN STOCK";
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "OUT OF STOCK";
	}
	public String getInventoryName(int productId) {
		IProduct p;
		try {
			p = productService.searchById(productId);
			if(p.isAvailability()) {
				return "instock";
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "outofstock";
	}
	
public int getRatingByProduct(int productId) {
		
		try {
			return feedBack.getRatingByProduct(productId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
public IPurshase purchase(int productId) {
	try {
		
		try {
			HttpSession session = SessionUtils.getSession();
	        int emp1= (int) session.getAttribute("id");
	        int saleId= saleService.getCurrentSaleByProduct(productId).getId();
			return purshaseService.addPurshase(emp1, saleId);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	
}
public List<IProduct> getSales() {
	List<IProduct> prod= new ArrayList<>();
	try {
		HttpSession session = SessionUtils.getSession();
	    int emp1= (int) session.getAttribute("id");
	    List<ISale> s;
	    
		s = saleService.getSalesByEmployee(emp1);
		  if(s != null) {
		    	for(ISale sales: s) {
		    		IProduct p= productService.searchById(sales.getProductId());
		    		if(p != null) {
		    			prod.add(p);
		    		}
		    	}
		    }
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  
	return prod;
}
    
	

}
