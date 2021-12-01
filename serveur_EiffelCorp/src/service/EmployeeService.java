package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;


import entity.Request;
import shared.Employee;
import shared.IEmployeeController;
import shared.IRequestObserver;


public class EmployeeService extends UnicastRemoteObject implements IEmployeeController{
	Map<Integer,Employee> employees;
	private int idCounter= 0;
	protected EmployeeService() throws RemoteException {
		super();
		employees= new HashMap<Integer, Employee>();
	}
	private static EmployeeService single_instance = null;

	public static EmployeeService GetInstance() throws RemoteException {
		if (single_instance == null) {
			single_instance = new EmployeeService();
		}
		return single_instance;
	}

	@Override
	public Employee addEmployee(String firstName, String lastName, String email, String address, String login, String password)
			throws RemoteException {
		Employee employee= new Employee(getIdCounter(), firstName, lastName, email, address, login, password);
		employees.put(employee.getId(), employee);
		return employee;
	}

	@Override
	public boolean removeEmployee(int id) throws RemoteException {
		employees.remove(id);
		return true;
	}

	@Override
	public Employee searchEmployeeById(int id) throws RemoteException {
		
		return employees.get(id);
	}
	public int getIdCounter() {
		idCounter++;
		return idCounter;
	}

	@Override
	public Employee login(String login, String password) throws RemoteException {
		for(Map.Entry<Integer,Employee> emp : employees.entrySet())
		{
			if(emp.getValue().getLogin().equals(login) && emp.getValue().getPassword().equals(password)) {
				return emp.getValue();
			}
		}
		return null;
	}

	@Override
	public IRequestObserver addRequest(int idProduct, int employeeId) throws RemoteException {
		IRequestObserver request= new Request(idProduct, employeeId);
		return request;
	}


}
