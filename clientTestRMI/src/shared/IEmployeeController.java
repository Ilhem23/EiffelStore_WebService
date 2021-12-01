package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IEmployeeController extends Remote{
	
	public Employee addEmployee(String firstName, String lastName, String email, String address, String login, String password) throws RemoteException;
	
	public boolean removeEmployee(int id) throws RemoteException;
	
	public Employee searchEmployeeById(int id) throws RemoteException;
	
	public Employee login(String login, String password) throws RemoteException;
	
	public IRequestObserver addRequest(int idProduct, int employeeId) throws RemoteException;
}

