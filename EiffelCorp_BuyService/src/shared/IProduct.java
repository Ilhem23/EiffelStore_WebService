package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IProduct extends Remote{

	public int getId() throws RemoteException;

	public void setId(int id) throws RemoteException;

	public String getName() throws RemoteException;

	public void setName(String name) throws RemoteException;

	public String getType() throws RemoteException;

	public void setType(String type) throws RemoteException;

	public String getCategory() throws RemoteException;

	public void setCategory(String category) throws RemoteException;

	public boolean isAvailability() throws RemoteException;

	public void setAvailability(boolean availability) throws RemoteException;

	public String getImage() throws RemoteException;

	public void setImage(String image) throws RemoteException;

	public int getPrice() throws RemoteException;

	public void setPrice(int price) throws RemoteException;

	public String getColor() throws RemoteException;

	public void setColor(String color) throws RemoteException;

	public String getState() throws RemoteException;

	public void setState(String state) throws RemoteException;


}