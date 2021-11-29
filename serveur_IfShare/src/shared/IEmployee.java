package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IEmployee extends Remote{

public int getId() throws RemoteException;

public void setId(int id) throws RemoteException;

public String getLogin() throws RemoteException;

public void setLogin(String login) throws RemoteException;

public String getPassword() throws RemoteException;

public void setPassword(String password) throws RemoteException;

public String getFirstName() throws RemoteException;

public void setFirstName(String firstName) throws RemoteException;

public String getLastName() throws RemoteException;

public 	void setLastName(String lastName) throws RemoteException;

public 	String getEmail() throws RemoteException;

public 	void setEmail(String email) throws RemoteException;

public 	String getAddress() throws RemoteException;

public 	void setAddress(String address) throws RemoteException;

public List<Integer> getInbox() throws RemoteException;

public void setInbox(List<Integer> inbox) throws RemoteException;

public void addToInbox(int idProduct) throws RemoteException;

}