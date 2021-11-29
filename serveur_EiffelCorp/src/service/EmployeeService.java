package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import entity.Employee;
import shared.IEmployee;
import shared.IEmployeeController;


public class EmployeeService extends UnicastRemoteObject implements IEmployeeController{
	Map<Integer,IEmployee> employees;
	private int idCounter= 0;
	protected EmployeeService() throws RemoteException {
		super();
		employees= new HashMap<Integer, IEmployee>();
	}
	private static EmployeeService single_instance = null;

	public static EmployeeService GetInstance() throws RemoteException {
		if (single_instance == null) {
			single_instance = new EmployeeService();
		}
		return single_instance;
	}

	@Override
	public IEmployee addEmployee(String firstName, String lastName, String email, String address, String login, String password)
			throws RemoteException {
		IEmployee employee= new Employee(getIdCounter(), firstName, lastName, email, address, login, password);
		employees.put(employee.getId(), employee);
		return employee;
	}

	@Override
	public boolean removeEmployee(int id) throws RemoteException {
		employees.remove(id);
		return true;
	}

	@Override
	public IEmployee searchEmployeeById(int id) throws RemoteException {
		
		return employees.get(id);
	}
	public int getIdCounter() {
		idCounter++;
		return idCounter;
	}

	@Override
	public void notifyChange(int idEmployee, int idProduct) throws RemoteException {
		employees.get(idEmployee).addToInbox(idProduct);
	}

	@Override
	public void removeNotify(int idEmployee, int idProduct) throws RemoteException {
		
		employees.get(idEmployee).addToInbox(idProduct);
	}

}
