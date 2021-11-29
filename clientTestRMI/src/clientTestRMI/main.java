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
		IEmployee emp1= employeeService.addEmployee("Ilhem", "Aissaoui", "ei_aissaoui@esi.dz", "5 impasse honore de balzac", "Ilhem23", "Ilhem23");
		IEmployee emp2= employeeService.addEmployee("Dounia", "Ksiaa", "donia.ksiaa@esprit.tn ", "5 torcy", "dounia23", "dounia23");
		IEmployee emp3= employeeService.addEmployee("Billel", "diab", "billel.2545@gmail.com", "5 noisey le grand", "billel23", "billel23");
		IEmployee emp4= employeeService.addEmployee("ghada", "benfliss", "ghada5@gmail.com", "5 noisey le grand", "ghada23", "ghada23");
		
		int id = productService.addProduct("tshirt1", "pull", "clothes", true, "tshirt.jpg", 12, "red", "good");
		int id2 = productService.addProduct("tshirt2", "pull", "clothes", true, "tshirt.jpg", 2, "red", "good");
		int id3 = productService.addProduct("tshirt3", "pull", "clothes", true, "tshirt.jpg", 13, "red", "good");
		
		System.out.println("id of product 1 " + productService.searchById(id).getId());
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
