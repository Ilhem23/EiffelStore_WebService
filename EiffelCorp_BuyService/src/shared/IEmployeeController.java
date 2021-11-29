package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IEmployeeController extends Remote{
	
	public IEmployee addEmployee(String firstName, String lastName, String email, String address, String login, String password) throws RemoteException;
	
	public boolean removeEmployee(int id) throws RemoteException;
	
	public IEmployee searchEmployeeById(int id) throws RemoteException;
	
	public void notifyChange(int idEmployee, int idProduct)throws RemoteException;

	void removeNotify(int idEmployee, int idProduct) throws RemoteException;
	
}
