package serveur_EiffelCorp;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import service.EmployeeService;
import shared.Employee;



public class EiffelCorpServer {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
	
		
		LocateRegistry.createRegistry(1100);
		EmployeeService employeeService= EmployeeService.GetInstance();
		Employee emp1= employeeService.addEmployee("Ilhem", "Aissaoui", "ei_aissaoui@esi.dz", "5 impasse honore de balzac", "Ilhem23", "Ilhem23");
		Employee emp2= employeeService.addEmployee("Dounia", "Ksiaa", "donia.ksiaa@esprit.tn ", "5 torcy", "dounia23", "dounia23");
		Employee emp3= employeeService.addEmployee("Billel", "diab", "billel.2545@gmail.com", "5 noisey le grand", "billel23", "billel23");
		Employee emp4= employeeService.addEmployee("ghada", "benfliss", "ghada5@gmail.com", "5 noisey le grand", "ghada23", "ghada23");
		
		
		
		Naming.rebind("rmi://localhost:1100/employeeService", employeeService);
	}

}
