package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IProductObserved extends Remote{
	public void subscribe(IRequestObserver obs) throws RemoteException;
	
}
