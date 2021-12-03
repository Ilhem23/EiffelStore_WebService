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
		
		// recover the services from the RMI serveur : productService, saleService, purshaseService, employeeService, feedBack
		productService = (IProductController) Naming.lookup("rmi://localhost:1099/productService");
		saleService = (ISaleController) Naming.lookup("rmi://localhost:1099/saleService");
		purshaseService = (IPurshaseController) Naming.lookup("rmi://localhost:1099/purshaseService");
		employeeService = (IEmployeeController) Naming.lookup("rmi://localhost:1100/employeeService");
		feedBack = (IFeedbackController) Naming.lookup("rmi://localhost:1099/feedBack");
		
		// get the the four employee of the company ( passing by value)
		Employee emp1= employeeService.searchEmployeeById(1);
		Employee emp2= employeeService.searchEmployeeById(2);
		Employee emp3= employeeService.searchEmployeeById(3);
		Employee emp4= employeeService.searchEmployeeById(4); 
		
		
		// add sale of products to employees who sale the product
		ISale saleId1 = saleService.addSale(1, emp1.getId());
		ISale saleId2 = saleService.addSale(2, emp2.getId());
		ISale saleId3 = saleService.addSale(3, emp3.getId());
		ISale saleId4 = saleService.addSale(4, emp1.getId());
		ISale saleId5 = saleService.addSale(5, emp2.getId());
		ISale saleId6 = saleService.addSale(6, emp3.getId());
		ISale saleId7 = saleService.addSale(7, emp1.getId());
		ISale saleId8 = saleService.addSale(8, emp2.getId());
		ISale saleId9 = saleService.addSale(9, emp3.getId());
		ISale saleId10 = saleService.addSale(10, emp1.getId());
		ISale saleId11 = saleService.addSale(11, emp2.getId());
		ISale saleId12 = saleService.addSale(12, emp3.getId());
		ISale saleId13 = saleService.addSale(13, emp4.getId());
		ISale saleId14 = saleService.addSale(14, emp4.getId());
		ISale saleId15 = saleService.addSale(15, emp4.getId());
		ISale saleId16 = saleService.addSale(16, emp4.getId());
		ISale saleId17 = saleService.addSale(17, emp4.getId());
		
		
		// show the list of sales : id sale with id of employee and id of product
		List<ISale> sales = saleService.getSales();
		for (ISale s : saleService.getSales()) {
			System.out.println(
					"sale n= : " + s.getId() + "de l'employee: " + s.getEmployeeId() + "product: " + s.getProductId());
			System.out.println(" State: " + s.isState());
		}
		
		
		
		// purchase all products by all employees
		
		IPurshase pursh1 = purshaseService.addPurshase(emp4.getId(), saleId1.getId());
		IPurshase pursh2 = purshaseService.addPurshase(emp4.getId(), saleId2.getId());
		IPurshase pursh3 = purshaseService.addPurshase(emp4.getId(), saleId3.getId());
		IPurshase pursh4 = purshaseService.addPurshase(emp4.getId(), saleId4.getId());
		IPurshase pursh5 = purshaseService.addPurshase(emp4.getId(), saleId5.getId());
		IPurshase pursh6 = purshaseService.addPurshase(emp3.getId(), saleId6.getId());
		IPurshase pursh7 = purshaseService.addPurshase(emp2.getId(), saleId7.getId());
		IPurshase pursh8 = purshaseService.addPurshase(emp1.getId(), saleId8.getId());
		IPurshase pursh9 = purshaseService.addPurshase(emp2.getId(), saleId9.getId());
		IPurshase pursh10 = purshaseService.addPurshase(emp3.getId(), saleId10.getId());
		IPurshase pursh11 = purshaseService.addPurshase(emp1.getId(), saleId11.getId());
		IPurshase pursh12 = purshaseService.addPurshase(emp2.getId(), saleId12.getId());
		IPurshase pursh13 = purshaseService.addPurshase(emp2.getId(), saleId13.getId());
		IPurshase pursh14 = purshaseService.addPurshase(emp1.getId(), saleId14.getId());
		IPurshase pursh15 = purshaseService.addPurshase(emp3.getId(), saleId15.getId());
		IPurshase pursh16 = purshaseService.addPurshase(emp2.getId(), saleId16.getId());
		IPurshase pursh17 = purshaseService.addPurshase(emp1.getId(), saleId17.getId());
		
		// show the purchase of sale 1 
		System.out.println("id " + pursh1.getId() + " employee " + pursh1.getEmployeeId());
		
		
		// request a not available product
		// create a observers 
		IRequestObserver request1= employeeService.addRequest(saleId1.getProductId(), emp3.getId());
		IRequestObserver request2= employeeService.addRequest(saleId2.getProductId(), emp1.getId());
		IRequestObserver request3= employeeService.addRequest(saleId3.getProductId(), emp2.getId());
		IRequestObserver request4= employeeService.addRequest(saleId9.getProductId(), emp4.getId());
		
		
		// subscribe the observers to the the corresponding products
		
		productService.searchByIdObserved(saleId1.getProductId()).subscribe(request1);
		productService.searchByIdObserved(saleId2.getProductId()).subscribe(request2);
		productService.searchByIdObserved(saleId3.getProductId()).subscribe(request3);
		productService.searchByIdObserved(saleId9.getProductId()).subscribe(request4);
		
		
		// add feedback to the product upon resale
				feedBack.addFeedBack(saleService.getSaleById(pursh1.getIdSale()).getProductId(), pursh1.getEmployeeId(), 5, "very good quality");
				feedBack.addFeedBack(saleService.getSaleById(pursh2.getIdSale()).getProductId(), pursh1.getEmployeeId(), 4, "good quality");
				feedBack.addFeedBack(saleService.getSaleById(pursh3.getIdSale()).getProductId(), pursh1.getEmployeeId(), 3, "well it is good");
				feedBack.addFeedBack(saleService.getSaleById(pursh9.getIdSale()).getProductId(), pursh1.getEmployeeId(), 3, "good sale");
		
		// resale of products that employees make a request with change of price and the state
		
		ISale resal1= saleService.reSale(pursh1.getId(), 40, "used - like new");
		ISale resal2= saleService.reSale(pursh2.getId(), 55, "Used - good condition");
		ISale resal3= saleService.reSale(pursh3.getId(), 60, "used - like new");
		ISale resal4= saleService.reSale(pursh9.getId(), 70, "used - like new");
		
	
		// show the notification inbox of employees after the resale
		List<Integer> inbox1= employeeService.searchEmployeeById(emp1.getId()).getInbox();
		List<Integer> inbox2= employeeService.searchEmployeeById(emp2.getId()).getInbox();
		List<Integer> inbox3= employeeService.searchEmployeeById(emp3.getId()).getInbox();
		List<Integer> inbox4= employeeService.searchEmployeeById(emp4.getId()).getInbox();
      
		for(int in : inbox1)
       {
    	   System.out.println("The product "+in+" is available for employee "+emp3.getId());
       }
		for(int in : inbox2)
	       {
	    	   System.out.println("The product "+in+" is available for employee "+emp4.getId());
	       }
		for(int in : inbox3)
	       {
	    	   System.out.println("The product "+in+" is available for employee "+emp4.getId());
	       }
		for(int in : inbox4)
	       {
	    	   System.out.println("The product "+in+" is available for employee "+emp4.getId());
	       }
		
	}

}
