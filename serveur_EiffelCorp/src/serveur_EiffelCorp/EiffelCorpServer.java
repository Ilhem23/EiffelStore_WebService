package serveur_EiffelCorp;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import service.EmployeeService;



public class EiffelCorpServer {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		LocateRegistry.createRegistry(1100);
		EmployeeService employeeService= EmployeeService.GetInstance();
		Naming.rebind("rmi://localhost:1100/employeeService", employeeService);
	}

}
