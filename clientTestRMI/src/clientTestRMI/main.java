package clientTestRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import shared.*;

public class main {
	public static IProductController productService = null;
	public static ISaleController saleService = null;
	public static IPurshaseController purshaseService = null;
	public static IEmployeeController employeeService = null;
	public static IFeedbackController feedBack = null;
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

		productService = (IProductController) Naming.lookup("rmi://localhost:1099/productService");
		saleService = (ISaleController) Naming.lookup("rmi://localhost:1099/saleService");
		purshaseService = (IPurshaseController) Naming.lookup("rmi://localhost:1099/purshaseService");
		employeeService = (IEmployeeController) Naming.lookup("rmi://localhost:1100/employeeService");
		feedBack = (IFeedbackController) Naming.lookup("rmi://localhost:1099/feedBack");
		Employee emp1= employeeService.searchEmployeeById(1);
		Employee emp2= employeeService.searchEmployeeById(2);
		Employee emp3= employeeService.searchEmployeeById(3);
		Employee emp4= employeeService.searchEmployeeById(4);
		
		int id = productService.addProduct("tshirt1",  "clothes", "description", true, "purple-t-shirt.jpg", 12, "good");
		int id2 = productService.addProduct("tshirt2",  "clothes", "description", true, "teal-t-shirt.jpg", 2, "good");
		int id3 = productService.addProduct("shoes",  "shoes", "description", true, "shoes.jpg", 13, "good");
		
		System.out.println("id of product 1 " + productService.searchById(id).getId()+ productService.searchById(id).getDescription());
		System.out.println("id of product 2 " + productService.searchById(id2).getId());
		System.out.println("id of product 3 " + productService.searchById(id3).getId());
		
		ISale saleId1 = saleService.addSale(id, emp1.getId());
		ISale saleId2 = saleService.addSale(id2, emp2.getId());
		ISale saleId3 = saleService.addSale(id3, emp3.getId());
		
		List<ISale> sales = saleService.getSales();
		for (ISale s : saleService.getSales()) {
			System.out.println(
					"sale n= : " + s.getId() + "de l'employee: " + s.getEmployeeId() + "product: " + s.getProductId());
			System.out.println(" State: " + s.isState());
		}
		IPurshase pursh = purshaseService.addPurshase(emp3.getId(), saleId1.getId());

		System.out.println("id " + pursh.getId() + "employee " + pursh.getEmployeeId());
		for (ISale s : saleService.getSales()) {
			System.out.println(
					"sale n= : " + s.getId() + "de l'employee: " + s.getEmployeeId() + "product: " + s.getProductId());
			System.out.println(" State: " + s.isState());
		}
		feedBack.addFeedBack(saleService.getSaleById(pursh.getIdSale()).getProductId(), pursh.getEmployeeId(), 2, "mediorque");
		saleService.reSale(pursh.getId(), 5, "mediorque");

		System.out.println(
				"purshase id " + pursh.getId() + "employee " + pursh.getEmployeeId() + "Etat: " + pursh.isState());
		for (ISale s : saleService.getSales()) {
			System.out.println(
					"sale n= : " + s.getId() + "de l'employee: " + s.getEmployeeId() + "product: " + s.getProductId());
			System.out.println(" State: " + s.isState());
		}
		for (int sold : saleService.getSoldAtLeastOnes()) {
			System.out.println("id of products " + sold);
		}
		
		IPurshase pursh2 = purshaseService.addPurshase(emp1.getId(), saleId2.getId());
 
		IPurshase pursh3 = purshaseService.addPurshase(emp3.getId(), saleId2.getId());
    
		IPurshase pursh4 = purshaseService.addPurshase(emp4.getId(), saleId2.getId());
		
		IPurshase pursh7 = purshaseService.addPurshase(emp4.getId(), saleId2.getId());
		
		if(pursh3 == null)
		{
			System.out.println("the product is not available for employee "+emp3.getId()+", you will be notify when the product is available");
		}
		if(pursh4 == null)
		{
			System.out.println("the product is not available for employee "+emp4.getId()+", you will be notify when the product is available");
		}
		
		IRequestObserver request1= employeeService.addRequest(saleId2.getProductId(), emp3.getId());
		IRequestObserver request2= employeeService.addRequest(saleId2.getProductId(), emp4.getId());
		
		productService.searchByIdObserved(saleId2.getProductId()).subscribe(request1);
		productService.searchByIdObserved(saleId2.getProductId()).subscribe(request2);
		
		feedBack.addFeedBack(saleService.getSaleById(pursh2.getIdSale()).getProductId(), pursh2.getEmployeeId(), 5, "good quality");
		int ip= saleService.getSaleById(pursh2.getIdSale()).getProductId();
		for(IFeedBack f: feedBack.searchByProduct(ip)) {
			
			System.out.println("The comment for product  "+ip+" by employee "+f.getEmployeeId()+" is: "+ f.getComment());
		}
		
		
		ISale sal= saleService.reSale(pursh2.getId(), 13, "bonne");
		
		List<Integer> inbox= employeeService.searchEmployeeById(emp3.getId()).getInbox();
		
		List<Integer> inbox2= employeeService.searchEmployeeById(emp4.getId()).getInbox();
      
		for(int in : inbox)
       {
    	   System.out.println("The product "+in+" is available for employee "+emp3.getId());
       }
		for(int in : inbox2)
	       {
	    	   System.out.println("The product "+in+" is available for employee "+emp4.getId());
	       }
		IPurshase pursh5 = purshaseService.addPurshase(emp3.getId(), sal.getId());
		feedBack.addFeedBack(saleService.getSaleById(pursh5.getIdSale()).getProductId(), pursh5.getEmployeeId(), 2, "mediorque");
		
		ISale sal2= saleService.reSale(pursh5.getId(), 30, "bonne");
		
		List<Integer> inbox3= employeeService.searchEmployeeById(emp3.getId()).getInbox();
		
		List<Integer> inbox4= employeeService.searchEmployeeById(emp4.getId()).getInbox();
      
		for(int in : inbox3)
       {
    	   System.out.println("The product "+in+" is available for employee "+emp3.getId());
       }
		for(int in : inbox4)
	       {
	    	   System.out.println("The product "+in+" is available for employee "+emp4.getId());
	       }
	}

}
