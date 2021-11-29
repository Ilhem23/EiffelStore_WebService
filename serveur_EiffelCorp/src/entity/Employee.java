package entity;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import shared.IEmployee;

public class Employee extends UnicastRemoteObject implements IEmployee{
	
	private int id;
	private String firstName;
	private String lastName;
	private String  email;
	private String address;
	private String login;
	private String password;
	private List<Integer> inbox;
	
	protected Employee() throws RemoteException {
		super();
	}
	public Employee(int id, String firstName, String lastName, String email, String address, String login, String password) throws RemoteException {
		super();
		this.id = id;
		this.firstName= firstName;
		this.lastName= lastName;
		this.email= email;
		this.address= address;
		this.login= login;
		this.password= password;
		inbox= new ArrayList<Integer>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public List<Integer> getInbox() {
		return inbox;
	}
	public void setInbox(List<Integer> inbox) {
		this.inbox = inbox;
	}
	public void addToInbox(int productNotif) {
		this.inbox.add(productNotif);
		
	}
	public void removeFromInbox(int productNotif) {
		this.inbox.remove(productNotif);	
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

}
